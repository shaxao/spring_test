package leetcode.duoxiancheng;

import java.util.concurrent.locks.Lock;

/**
 * H2O 生成
 */
public class H2O {
    private static final Object lock = new Object();
    public static void main(String[] args) {
        synchronized (lock){
            try {
                lock.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notifyAll();
        }
    }
}
