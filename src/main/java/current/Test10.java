package current;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test10")
public class Test10 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

        Condition condition = lock.newCondition();
    }
}
