package org.framework.utils;

/**
 * Created by windy on 2016/9/19.
 */
public class CastUtil {

    /**
     * 转为String（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj,String defaultValue)
    {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为String型
     * @param obj
     * @return
     */
    public static String castString(Object obj)
    {
        return CastUtil.castString(obj,"");
    }

    public static long castLong(Object obj,long defaultValue)
    {
        long longValue = defaultValue;
        if(obj != null)
        {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue))
            {
                longValue = Long.parseLong(strValue);
            }
        }
        return longValue;
    }

    public static long castLong(Object obj)
    {
       return castLong(obj,0);
    }

}
