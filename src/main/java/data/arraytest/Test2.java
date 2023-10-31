package data.arraytest;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int[] arr = {2,5,1,8,9,4,8};
        int[] newArr = referenceOrder(arr);
        System.out.println(Arrays.toString(newArr));
        int a = 945;
        int reverse = Integer.reverse(a);
        int i = Integer.reverseBytes(a);
        System.out.println(i);
    }

    public static int[] referenceOrder(int[] arr){
        //创建一个新数组，将前后数据进行交换
        int[] newArr = new int[arr.length];
        for(int i = 0;i < arr.length;i++){
            newArr[i] = arr[arr.length -1 -i];
        }
        return newArr;
    }
}
