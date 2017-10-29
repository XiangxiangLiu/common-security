package com.boy.security.util;

public class StringUtil {

    public static boolean isEmpty (String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean validate(String ...args) {
        if (args == null) {
            return true;
        }
        for (String arg : args) {
            if (isEmpty(arg)) {
                return false;
            }
        }
        return true;
    }


}
