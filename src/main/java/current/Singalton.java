package current;

public class Singalton {
    private static Singalton singalton;

    public static Singalton getSingalton(){
        //if (singalton == null) {
            //synchronized (Singalton.class) {
                if (singalton == null) {
                    singalton = new Singalton();
                }
           // }
        //}
        return singalton;
    }

    public static void main(String[] args) {
        new Thread(() -> {

            Singalton singalton = getSingalton();
            System.out.println(Thread.currentThread()+" "+singalton);
        },"t1").start();
        new Thread(() -> {
            Singalton singalton = getSingalton();
            System.out.println(Thread.currentThread()+" "+singalton);
        },"t2").start();
    }
}
