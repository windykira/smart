package org.framework.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by windy on 2016/9/20.
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection)
    {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection)
    {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?,?> map)
    {
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?,?> map)
    {
        return !isEmpty(map);
    }
}
