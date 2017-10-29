package com.boy.security.hash;


import com.boy.security.model.HashAlgorithmEnum;
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

    public static String calcuteHash(HashAlgorithmEnum hashAlg, byte[] srcData) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(hashAlg.getHashAlgName());
        digest.reset();
        digest.update(srcData);
        byte[] hbyte = digest.digest();
        return Base64Util.encode(hbyte);
    }

}
