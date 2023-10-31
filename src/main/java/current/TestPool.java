package current;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,TimeUnit.SECONDS,1,1,(queue,task) -> {
            //1.死等
            //queue.put(task);
            //2.带超时等待
            queue.offer(task,1500,TimeUnit.MILLISECONDS);
            //3.让调用者放弃任务执行
           // log.debug("放弃了");
            //4.让调用者抛出异常
            //throw new RuntimeException("任务执行失败" + task);
            //5.让调用者自己执行任务
            //task.run();
        });
        for (int i = 0; i < 3; i++) {
            int j = i;
           threadPool.execute(() -> {
               try {
                   Thread.sleep(1000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               log.debug("{}",j);
           });
        }
    }
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //获取任务时的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    //核心线程数
    private int coreSize;

    private RejectPolicy<Runnable> rejectPolicy;

    //执行任务
    public void execute(Runnable task){
        // 当任务数没有超过核心线程数时，直接交给worker对象
        // 当任务数超过核心线程数是，暂时放到任务队列
        synchronized (workers){
            if(workers.size() < coreSize){
                Worker worker = new Worker(task);
                log.debug("创建任务对象{},{}",worker,task);
                workers.add(worker);
                worker.start();
            }else {
//                log.debug("往任务队列放入任务{}",task);
//                taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy,task);
            }
        }
    }

    public ThreadPool(long timeout, TimeUnit timeUnit, int coreSize,int queueCapcity,RejectPolicy<Runnable> rejectPolicy) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;
    }
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task){
            this.task = task;
        }

        @Override
        public void run() {
            // 当task不为空，直接执行
            // 当task为空，从任务队列拉去任务执行
            //while (task != null || (task = taskQueue.take()) != null){
            while (task != null || (task = taskQueue.poll(timeout,timeUnit)) != null){
                try {
                    log.debug("正在执行{}....",task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }
            synchronized (workers){
                log.debug("从任务队列移除工作对象");
                workers.remove(this);
            }

        }
    }

}

@FunctionalInterface //拒绝策略
interface RejectPolicy<T> {
     void reject(BlockingQueue<T> queue,T task);
}


@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();
    //2.锁
    private ReentrantLock lock = new ReentrantLock();
    //3.生产者条件变量
    private Condition proCondition = lock.newCondition();
    //4.消费者条件变量
    private Condition comCondition = lock.newCondition();
    //5.容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try {
            long nacos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()){
                try {
                    if(nacos <= 0){
                        return null;
                    }
                    nacos = comCondition.awaitNanos(nacos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            proCondition.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take(){
        lock.lock();
        try {
           while (queue.isEmpty()){
               try {
                   comCondition.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
            T t = queue.removeFirst();
            proCondition.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T element){
        lock.lock();
        try {
            while (queue.size() == capcity){
                try {
                    log.debug("等待加入阻塞队列{}",element);
                    proCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            comCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    //带超时时间阻塞添加
    public boolean offer(T task,long timeout,TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity){
                try {
                    log.debug("等待加入队列{}",task);
                    if(nanos <= 0){
                        return false;
                    }
                   nanos = proCondition.awaitNanos(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入阻塞队列{}",task);
            queue.addLast(task);
            comCondition.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    //获取容量大小
    public int getSize(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if(queue.size() == capcity){
                rejectPolicy.reject(this,task);
            }else { // 有空闲
                log.debug("加入阻塞队列{}",task);
                queue.addLast(task);
                comCondition.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}

