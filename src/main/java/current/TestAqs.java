package current;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.TestAqs")
public class TestAqs {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
    }
}

class MyLock implements Lock {

    class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override //是否持有独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private MySync sync = new MySync();

    @Override //加锁（不成功会进入同步队列）
    public void lock() {
        sync.acquire(1);
    }

    @Override //加锁，可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override //尝试加锁（一次）
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override //尝试加锁，带超时
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override //解锁
    public void unlock() {
        sync.release(1);
    }

    @Override //创建条件变量
    public Condition newCondition() {
        return newCondition();
    }
}