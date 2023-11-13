package data.treeNode;

import java.util.*;

//数组表示下的二叉树类
public class ArrayBinaryTree {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(0,1);
        map.put(1,5);
        map.put(2,9);
        Set set = map.keySet();
        int a = 255;
        Date date = new Date();
        LinkedList list = new LinkedList();
        List<String> list1 = new ArrayList<>();
        String s = "13";
        char[] c = {'s','d','s'};
        c[1] = ' ';
        int i = Integer.parseInt(s);
        int[] nu =  {1,2,3};
        int[][] ll = new int[4][4];
        ll[0][0] = 1;
        ll[0][1] = 2;
        ll[0][2] = 3;
        ll[0][3] = 4;
        ll[1][0] = 1;
        ll[1][1] = 2;
        ll[1][2] = 3;
        ll[1][3] = 4;
        for(int[] l:ll){
            System.out.println(l[1]);
        }
        long time = date.getTime();
        System.out.println();
    }
}
