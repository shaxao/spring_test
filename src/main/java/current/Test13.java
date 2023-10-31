package current;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test13")
public class Test13 {
    public static void main(String[] args) {
        waitNotify waitNotify = new waitNotify(5);
        Condition a = waitNotify.newCondition();
        Condition b = waitNotify.newCondition();
        Condition c = waitNotify.newCondition();

        new Thread(() -> {
            waitNotify.print("a",a,b);
        }).start();
        new Thread(() -> {
            waitNotify.print("b",b,c);
        }).start();
        new Thread(() -> {
            waitNotify.print("c",c,a);
        }).start();

        Sleeper.sleep(1);
        waitNotify.lock();
        try {
            log.debug("开始.....");
            a.signal();
        }finally {
            waitNotify.unlock();
        }
    }
}

class waitNotify extends ReentrantLock{
    private int loopNumber;

    public waitNotify(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str,Condition current,Condition next){
        for(int i = 0; i < loopNumber;i++){
            lock();
            try {
                current.await();
                System.out.println(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }

}
