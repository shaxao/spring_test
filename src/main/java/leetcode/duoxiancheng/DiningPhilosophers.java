package leetcode.duoxiancheng;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家就餐
 */
public class DiningPhilosophers {
    private ReentrantLock[] lockList = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
    };
    //最多允许4个哲学家就餐
    private Semaphore semaphore = new Semaphore(4);
    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        semaphore.acquire();

        lockList[leftFork].lock();
        lockList[rightFork].lock();

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();

        lockList[leftFork].unlock();
        lockList[rightFork].unlock();

        semaphore.release();
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        //diningPhilosophers.wantsToEat(1,() -> System.out.println(),);
        Semaphore semaphore = new Semaphore(5);
        try {
            semaphore.acquire();
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(semaphore.availablePermits());
        int i = 0;
        for(;i < 5;i++){
            System.out.println(i / 2 == 0);
        }
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
    }
}

