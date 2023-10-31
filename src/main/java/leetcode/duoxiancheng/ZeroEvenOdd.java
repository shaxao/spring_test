package leetcode.duoxiancheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 打印零和奇偶数
 */
public class ZeroEvenOdd {
    private int n;
    private volatile int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<=n; i++) {
            lock.lock();
            printNumber.accept(0);
            flag ++;
            condition.signalAll();
            while (flag %2 == 0) condition.await();
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i=2; i<=n; i+=2) {
            lock.lock();
            while (flag % 4 != 0) condition.await();
            printNumber.accept(i);
            flag ++;
            condition.signalAll();
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<=n; i+=2) {
            lock.lock();
            while (flag % 4 != 2) condition.await();
            printNumber.accept(i);
            flag ++;
            condition.signalAll();
            lock.unlock();
        }
    }
}
class Test1 {
    public static void main(String[] args) {

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero((n) -> System.out.print(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even((n) -> System.out.print(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd((n) -> System.out.print(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
    }
}
