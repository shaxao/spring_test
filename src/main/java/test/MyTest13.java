package test;


import java.util.*;

public class MyTest13 {
    public static void main(String[] args) {

        System.out.println(105 % 2);
    }


    public int[] getNext(String subStr){

        int[] next = new int[subStr.length()];

        next[0] = 1;

        int y = 0;
        int len = -1;

        while(y < next.length - 1){
            if(len == -1 || subStr.charAt(y) == subStr.charAt(len)){
                y++;
                len++;
                //表示当前字符串的共有元素个数
                next[y] = len;
            }else {
                len = next[len];
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        return next;
    }
}
