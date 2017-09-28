package com.boy.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

    public static String encode(byte[] bs) {
        return Base64.getEncoder().encodeToString(bs);
    }

    public static byte[] decode(String b64) {
        return Base64.getDecoder().decode(b64);
    }

    public static String decodeString(String b64) {
        String srcData = null;
        try {
            byte[] bs = Base64.getDecoder().decode(b64);
            srcData = new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return srcData;
    }

}
