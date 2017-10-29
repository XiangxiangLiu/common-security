package com.boy.security.hash;

import com.boy.security.model.HashAlgorithmEnum;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class HashAlgorithmTest {

    private static final String srcData = "刘祥祥";

    @Test
    public void do_md5_test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = HashAlgorithm.calcuteHash(HashAlgorithmEnum.MD5, srcData.getBytes("utf-8"));
        System.out.println("md5 = " + hash + ", length = " + hash.length());
    }

    @Test
    public void do_sha1_test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = HashAlgorithm.calcuteHash(HashAlgorithmEnum.SHA1, srcData.getBytes("utf-8"));
        System.out.println("sha1 = " + hash + ", length = " + hash.length());
    }

    @Test
    public void do_sha256_test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = HashAlgorithm.calcuteHash(HashAlgorithmEnum.SHA256, srcData.getBytes("utf-8"));
        System.out.println("sha256 = " + hash + ", length = " + hash.length());
    }
}
