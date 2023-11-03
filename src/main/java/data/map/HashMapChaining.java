package data.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HashMapChaining {
    int size;//键值对数量
    int capacity;//容量
    double loadThres;//触发扩容的负载因子阈值
    int extendRatio; //扩容倍数
    List<List<Pair>> buckets;//桶数组

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0;i < capacity;i++){
            buckets.add(new ArrayList<>());
        }
    }

    //哈希函数
    int hashFunc(int key){
        return key % capacity;
    }
    //负载因子
    double loadFactor(){
        return (double) size / capacity;
    }

    //查询
    String get(int key){
        int index = hashFunc(key);
        List<Pair> pairs = buckets.get(index);
        for(Pair pair:pairs){
            if(pair.key == key){
                return pair.value;
            }
        }
        return null;
    }

    //添加
    void put(int key,String value){
        if(loadFactor() > loadThres){
            //如果超出阈值，那么就扩容
            extend();
        }
        int index = hashFunc(key);
        List<Pair> pairs = buckets.get(index);
        for(Pair pair:pairs) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }
        Pair pair1 = new Pair(key, value);
        pairs.add(pair1);
        size++;
    }

    //删除
    void remove(int key) {
        int index = hashFunc(key);
        List<Pair> pairs = buckets.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                pairs.remove(pair);
                size--;
                break;
            }
        }
    }

        //扩容
        void extend () {
            //暂存数据
            List<List<Pair>> pairList = buckets;
            capacity *= extendRatio;
            buckets = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                buckets.add(new ArrayList<>());
            }
            size = 0;
            //把原来的数据复制到新数组中
            for (List<Pair> pairL : pairList) {
                for (Pair pair : pairL) {
                    put(pair.key, pair.value);
                }
            }
        }
        //打印哈希表
    void print(){
        for(List<Pair> pairs:buckets){
            List<String> list = new ArrayList<>();
            for (Pair pair:pairs){
                list.add("key ->"+pair.key+"value ->"+ pair.value);
            }
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        HashMapChaining map = new HashMapChaining();
        map.put(2365,"mm");
        map.put(3469,"mk");
        map.put(2589,"bf");
        map.put(7854,"sdd");

        String s = map.get(3469);
        System.out.println("3469:"+s);
        map.print();



        map.remove(3469);
        System.out.println("删除3469后数组");
        map.print();
    }
}

