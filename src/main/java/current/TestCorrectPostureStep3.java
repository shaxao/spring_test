package current;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static current.Sleeper.sleep;


@Slf4j(topic = "c.TestCorrectPosture")
public class TestCorrectPostureStep3 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    static ReentrantLock ROOM = new ReentrantLock();
    //抽烟的休息室
    static Condition waitCigaretteSet = ROOM.newCondition();
    //外卖的休息室
    static Condition waitWaiMai = ROOM.newCondition();
    // 虚假唤醒
    public static void main(String[] args) {
        new Thread(() -> {
            ROOM.lock();
            try {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活了");
                } else {
                    log.debug("没干成活...");
                }
            }finally {
                ROOM.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
           ROOM.lock();
           try {
               Thread thread = Thread.currentThread();
               log.debug("外卖送到没？[{}]", hasTakeout);
               if (!hasTakeout) {
                   log.debug("没外卖，先歇会！");
                   try {
                       waitWaiMai.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               log.debug("外卖送到没？[{}]", hasTakeout);
               if (hasTakeout) {
                   log.debug("可以开始干活了");
               } else {
                   log.debug("没干成活...");
               }
           }finally {
               ROOM.unlock();
           }
        }, "小女").start();

        sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                waitWaiMai.signal();
            }finally {
                ROOM.unlock();
            }
        }, "送外卖的").start();

        sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try {
                hasCigarette = true;
                log.debug("烟到了噢！");
                waitCigaretteSet.signal();
            }finally {
                ROOM.unlock();
            }
        }, "送烟的").start();
    }

}
