package secrict;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DesaDemo {
    public static void main(String[] args) throws Exception {
        //原文
        String input = "louwei";
        //密钥key
        String key = "12345678";
        //算法
        String algori = "DES";
        //加密规则
        String transformation = "DES";

        String s1 = encrptDES(input,key,algori,transformation);
        System.out.println(s1);

        String test = "xka7DD0GnGs=";
        String s2 = decryptDES(test,key,algori,transformation);
        System.out.println(s2);

    }

    /**
     * 解密
     * @param input
     * @param key
     * @param algori
     * @param transformation
     * @return
     */
    private static String decryptDES(String input, String key, String algori, String transformation) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algori);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.decode(input));
        return new String(bytes);
    }

    /**
     * 加密
     * @param input
     * @param key
     * @param algori
     * @param transformation
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encrptDES(String input, String key, String algori, String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algori);
        //初始化加密模式和加密算法
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        //输出加密后的数据
        String encode = Base64.encode(bytes);
        return encode;
    }
}
