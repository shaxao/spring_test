package test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.now;

public class MyTest9 {
    public static void main(String[] args) {
       long a = 2;
       int b = 2;
//        System.out.println(Long.toBinaryString(2));
//        System.out.println(Long.toBinaryString(a << 62));//10\0000\0000\0000\0000\0000\0000\0000\0000
        //System.out.println(Integer.toBinaryString((b << 20 | 2)));
        int c = 4;
        //System.out.println(b | c);
        System.out.println(Integer.toBinaryString(b)); // 10
        System.out.println(Integer.toBinaryString(c)); //100
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        System.out.println(format);
    }
}
