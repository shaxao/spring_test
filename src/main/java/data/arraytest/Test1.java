package data.arraytest;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        int[] arr = {1,5,7,8,4};
        referenceOrder(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void referenceOrder(int[] arr){
        //遍历数组，将前半部分数据和后半部分数据进行交换
        for(int i = 0;i < arr.length / 2;i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 -i];
            arr[arr.length - 1 -i] = temp;
        }
    }
}
