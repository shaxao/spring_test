package test;


public class MyTest {
    public static void main(String[] args) {

//        String s1 = "ab";
//        String st = new String("a") + new String("b");
//        st.intern(); //1） 当常量池中不存在"abc"这个字符串的引用，将这个对象的引用加入常量池，返回这个对象的引用。（2） 当常量池中存在"abc"这个字符串的引用，返回这个对象的引用；
//        System.out.println(st == s1); //true  此时intern引用的是st的内存地址并加入常量池，然后返回
//        System.out.println(st == st); //false 此时intern引用的是s1的地址
        Integer a = 10;
        Integer b = 10;
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        String s5 = "a" + "b";// Javac在编译期间的优化，因为是常量，结果在编译期间已经确定，会直接去常量池中去寻找”ab”。
        String s6 = "a";
        System.out.println(s3 == s4);// false 在底层会调用StringBuilder.append()方法，而StringBuilder底层为new String().s3会在常量池中，s4在堆中，因而false。
        System.out.println(s3 == s5); //true
        System.out.println(s1 == s6);//true
        System.out.println(a == b);

    }
}
