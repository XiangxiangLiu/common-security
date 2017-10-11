package com.boy.security.hash;


import com.boy.security.util.Base64Util;

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

    public static String sha1(String srcData) {
        return hash("SHA-1", srcData);
    }

    public static String sha256(String srcData) {
        return hash("SHA-256", srcData);
    }

    public static String hash(String hashAlgorithm, String srcData)  {
        String hashval = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            digest.reset();
            digest.update(srcData.getBytes());
            byte[] hbyte = digest.digest();
            hashval = Base64Util.encode(hbyte);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(hashAlgorithm + " hash algorithm no such algorithm exception.");
            e.printStackTrace();
        }
        return hashval;
    }

}
