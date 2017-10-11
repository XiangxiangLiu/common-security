package com.boy.security.hash;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法：
 *      安全散列算法：md5(128) sha1(256)等
 *      含有密钥散列算法：mac 等
 *
 *
 */
public class HashAlgorithm {

    public static String md5(String srcData) {
        return hash("MD5", srcData);
    }

    public static String md128(String srcData) {
        return hash("MD128", srcData);
    }

    public static String sha1(String srcData) {
        return hash("SHA1", srcData);
    }

    public static String sha256(String srcData) {
        return hash("SHA256", srcData);
    }

    public static String hash(String hashAlgorithm, String srcData)  {
        String hashval = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            digest.reset();
            digest.update(srcData.getBytes());
            byte[] hbyte = digest.digest();
            hashval = new String(hbyte);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("hash algorithm no such algorithm exception.");
            e.printStackTrace();
        }
        return hashval;
    }

}
