package com.boy.security.hash;

import org.junit.Test;

public class HashAlgorithmTest {

    private static final String srcData = "刘祥祥";

    @Test
    public void do_md5_test() {
        String hash = HashAlgorithm.md5(srcData);
        System.out.println("md5 = " + hash);
    }

    @Test
    public void do_sha1_test() {
        String hash = HashAlgorithm.sha1(srcData);
        System.out.println("sha1 = " + hash);
    }

    @Test
    public void do_sha256_test() {
        String hash = HashAlgorithm.sha256(srcData);
        System.out.println("sha256 = " + hash);
    }
}
