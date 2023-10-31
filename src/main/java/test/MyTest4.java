package test;


import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class MyTest4 {
    private static final int _4MB = 4 * 1024 * 1024;
    public static void main(String[] args) throws IOException {
        //但是我们不希望有null存在，所以需要配合引用队列删除软引用释放内存
        soft();
    }
    public static void soft(){
        List<SoftReference<byte[]>> list = new ArrayList<>();
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        for(int i = 0;i < 5;i++){
            //关联了软引用对象，当软引用所关联的byte[]对象被回收事，软引用自己会加入到queue中
            SoftReference<byte[]> reference = new SoftReference<>(new byte[_4MB],queue);
            System.out.println(reference.get());
            list.add(reference);
            System.out.println(list.size());//5
        }
        //获取先进入队列的软引用对象移除队列
        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null){
            list.remove(poll);
            poll = queue.poll();
        }
        System.out.println("循环结束：" + list.size());//5 -> 1
        for(SoftReference<byte[]> reference: list){
            System.out.println(reference.get()); //null null null null [B@677327b6 -> [B@677327b6
        }
    }
}
