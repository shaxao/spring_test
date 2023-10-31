package leetcode.duoxiancheng;

public class Gou {
    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(2);
        fooBar.foo(() -> {
            System.out.println("foo");
        });
        fooBar.bar(() -> {
            System.out.println("bar");
        });
    }
}
class FooBar {
    private int n;
    //等待标记
    private static volatile int flag = 1;
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        if(flag == 1){
            synchronized (this){
                for (int i = 0; i < n; i++) {
                    while(flag == 1){
                        try {
                            this.wait();
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    flag = 2;
                    this.notify();
                }
            }
        }
        try {
            this.wait();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        if(flag == 2){
            synchronized (this){
                for (int i = 0; i < n; i++) {
                    while(flag == 2){
                        try {
                            this.wait();
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printBar.run();
                    flag = 1;
                    this.notify();
                }
            }
        }
        try {
            this.wait();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
