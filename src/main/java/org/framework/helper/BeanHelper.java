package org.framework.helper;

import org.framework.utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by windy on 2016/9/20.
 */
public class BeanHelper {

    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        Object obj;
        for(Class<?> beanClass : classSet)
        {
            obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     * 获取bean映射
     */
    public static Map<Class<?>,Object> getBeanMap()
    {
        return BEAN_MAP;
    }

    /**
     * 获取bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls)
    {
        if(!BEAN_MAP.containsKey(cls))
        {
            throw new RuntimeException("can not get bean by class:"+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }
}
