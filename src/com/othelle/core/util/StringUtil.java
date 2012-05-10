package com.othelle.core.util;

/**
 * User: v.vlasov
 * Date: 12/19/11
 */
public class StringUtil {
    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}
