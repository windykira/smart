package org.framework.utils;

/**
 * Created by windy on 2016/9/19.
 */
public class StringUtil {

    public static boolean isNotEmpty(String str)
    {
        return str != null && !"".equals(str.trim());
    }
}
