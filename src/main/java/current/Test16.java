package current;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

@Slf4j(topic = "c.Test16")
public class Test16 {
    public static void main(String[] args) {
     //因为数据会被加到三级缓存中，因为图中数组数据会连续存储在缓存行中，同时cpu是并行运行的，根据缓存一致性协议，给数据的缓存行留空，是吗
    
    }
}

/**
 * 自定义原子整数
 */
class MyAtomicInteger {
    private volatile int value;
    private static final long unsafeOffset;
    private static final Unsafe UNSAFE;

    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
        unsafeOffset =  UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        return value;
    }

    public void decrement(int amount){
        int pre = this.value;
        int next = pre - amount;
        UNSAFE.compareAndSwapInt(this,unsafeOffset,value,next);
    }
}
