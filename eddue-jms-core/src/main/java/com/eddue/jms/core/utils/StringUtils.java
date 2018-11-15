package com.eddue.jms.core.utils;

/**
 * @author zzx
 * @Description TODO
 * @company www.eddue.com
 * @since 2018/11/14 16:17
 */
public class StringUtils {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
}
