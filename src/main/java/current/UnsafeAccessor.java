package current;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccessor {
    private static Unsafe unsafe;

    static {
        try {
            Field unsafe1 = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe1.setAccessible(true);
            unsafe = (Unsafe) unsafe1.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Unsafe getUnsafe(){
        return unsafe;
    }
}
