package org.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by windy on 2016/9/20.
 */
public class ArrayUtil {

    public static boolean isEmpty(Object[] array)
    {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array)
    {
        return !isEmpty(array);
    }
}
