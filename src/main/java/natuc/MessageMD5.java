package natuc;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageMD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(new BASE64Encoder().encode(MessageMD5.getMD5Password("louwei","MD5")));//paB13uagBqYGxew9rUKgdQ==

    }

    /**
     *
     * @param input 原文
     * @param algorithm 算法
     * @param transformation 加密类型
     * @return
     */
    private static byte[] getPassword(String input,String algorithm,String transformation){

        return null;
    }

    /**
     * 获取MD5消息摘要
     * @param input
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getMD5Password(String input,String algorithm) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(algorithm);
        byte[] digest = instance.digest(input.getBytes());
        return digest;
    }
}
