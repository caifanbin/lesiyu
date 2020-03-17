package com.binge.lesiyu.commons;

public class StringUtil {

    public static Boolean isEmpty(String str) {

        return str == null || "".equals(str);
    }

    public static Boolean iNotEmpty(String str) {
        return !isEmpty(str);
    }
}
