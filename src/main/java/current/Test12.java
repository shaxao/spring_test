package current;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test12")
public class Test12 {
    public static void main(String[] args) {
        waitNotify1 waitNotify = new waitNotify1(1, 5);
        new Thread(() -> {
            waitNotify.print("a",1,2);
        }).start();
        new Thread(() -> {
            waitNotify.print("b",2,3);
        }).start();
        new Thread(() -> {
            waitNotify.print("c",3,1);
        }).start();
    }
}
    /**
     * 交替输出
     * 输出内容        等待标记         下一个标记
     * a                1               2
     * b                2               3
     * c                3               1
     */
    class waitNotify1 {
        public void print(String str,int waitFlag,int nextFlag){
            synchronized (this){
                for(int i = 0;i < loopNumber;i++){
                    while (flag != waitFlag){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(str);
                    flag = nextFlag;
                    this.notifyAll();
                }
            }
        }

    //等待标记
    private int flag;
    //循环次数
    private int loopNumber;

        public waitNotify1(int flag, int loopNumber) {
            this.flag = flag;
            this.loopNumber = loopNumber;
        }

        public int getFlag() {
            return flag;
        }

        public int getLoopNumber() {
            return loopNumber;
        }
    }
