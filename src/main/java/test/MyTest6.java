package test;


import java.util.ArrayList;

public class MyTest6 {
    private static final int _512kb= 512 * 1024;
    private static final int _1MB= 1024 * 1024;
    private static final int _6MB= 6 * 1024 * 1024;
    private static final int _7MB= 7 * 1024 * 1024;
    private static final int _8MB= 8* 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            ArrayList<byte[]> list = new ArrayList<>();
            list.add(new byte[_8MB]);
            list.add(new byte[_8MB]);
        }).start();
        System.out.println("睡眠,,,,,,,,,");
        Thread.sleep(1000);
    }
}
