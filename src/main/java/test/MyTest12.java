package test;

public class MyTest12 {
    public static void main(String[] args) {
        lion lion = new lion();
        System.out.println(lion);
    }


}
class animal{

    static String naimal = "狮子";

    static {
        System.out.println("animal init");

    }
}

class lion extends animal{
    static {
        System.out.println("lion init");
    }

    public static void main(String[] args) {
        System.out.println("main init");
    }
}
