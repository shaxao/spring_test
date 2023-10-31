package test;

import lombok.extern.slf4j.Slf4j;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.MyTest14")
public class MyTest14 {
    public static void main(String[] args) {

    }
}
@Slf4j(topic = "c.ThreadPool")
class ThreadPool{

    private int maxNum;

    private int coreNum;

    private HashSet<Worker> workers = new HashSet<>();

    private RejectPolicy<Runnable> rejectPolicy;

    //获取任务时的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private BlockingQueue<Runnable> taskQueue;

    public void excute(Runnable task){
        synchronized (workers){
            if(workers.size() < coreNum){
                Worker worker = new Worker(task);
                log.debug("创建任务对象{}{}",task,worker);
                workers.add(worker);

            }
        }
    }

    public ThreadPool(int maxNum, int coreNum, RejectPolicy<Runnable> rejectPolicy,int queueCapcity,long timeout, TimeUnit timeUnit) {
        this.maxNum = maxNum;
        this.coreNum = coreNum;
        this.rejectPolicy = rejectPolicy;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
    }

    /**
     * 工作类
     */
    class Worker extends Thread{
        private Runnable task;
        public Worker(Runnable task){
            this.task = task;
        }
        @Override
        public void run() {

        }
    }
}

interface RejectPolicy<T>{
    void reject(BlockingQueue<T> queue, T task);
}

class BlockingQueue<T>{
    private int proxtity;

    private Deque<T> queue = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition conCondition = lock.newCondition();

    private Condition proCondition = lock.newCondition();

    public BlockingQueue(int proxtity) {
        this.proxtity = proxtity;
    }

    /**
     * 带超时的阻塞获取
     * @param time
     * @param timeUnit
     * @return
     */
    public T poll(long time, TimeUnit timeUnit){
        lock.lock();
        try {
            long nacos = timeUnit.toNanos(time);
            while (queue.isEmpty()){
                if (nacos <= 0){
                    return null;
                }
                try {
                    nacos = conCondition.awaitNanos(nacos);
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


    public void put(T element){
        lock.lock();
        try{
            while(queue.size() == proxtity){
                try {
                    proCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            conCondition.signal();
        }finally {
            lock.unlock();
        }
    }
}

