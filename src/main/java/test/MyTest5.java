package test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MyTest5 {
    private static final int _4MB = 4 * 1024 * 1024;
    public static void main(String[] args) {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for(int i = 0;i < 5;i++){
           //此时已经设置堆内存-Xmx20m,所以肯定会报堆内存溢出。如果是非核心业务，在内存的紧张的时候直接释放，需要的时候再读取，所以我们需要用到弱引用
            WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
            list.add(ref);
            for(WeakReference<byte[]> reference:list){
                //通过观察垃圾回收我们可以看到，前四次内存充足，对象都被放入，第五次时内存不足，回收第四次对象后，放入第五次对象
                System.out.println(reference.get()+"  ");//[B@1b6d3586 [B@4554617c [B@74a14482 null [B@677327b6

            }
       }
        System.out.println("循环结束"+list.size());//5
    }
}
