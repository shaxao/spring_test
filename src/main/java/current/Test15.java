package current;

import java.util.concurrent.atomic.AtomicInteger;

public class Test15 {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);
        int i = integer.updateAndGet(value -> value * 10);
        System.out.println(i);

    }
}
