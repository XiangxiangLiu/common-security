package com.boy.security.util;

public class Hex {

    public static String toHex(byte[] bs) {
        if (bs == null || bs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    public static byte[] toBinary(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }
        String hexString = hex.toUpperCase();
        int len = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bs = new byte[len];
        for (int i = 0; i < len; i ++) {
            int pos = i * 2;
            bs[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return bs;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
