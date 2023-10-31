package current;

public class Test {
    public static void main(String[] args) {
        Runnable t1 = () -> System.out.println("hh");
        Thread thread = new Thread(t1,"tt");
        thread.start();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
    }
}
