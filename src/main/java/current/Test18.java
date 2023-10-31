package current;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test18 {
    volatile static int x;
    static int y;
    public static void main(String[] args) {

        new Thread(()->{
            //x = 20;
            y = 10;
        },"t1").start();
        new Thread(()->{
            // x=20 对 t2 可见, 同时 y=10 也对 t2 可见
            System.out.println(x + y);
        },"t2").start();

    }
}
