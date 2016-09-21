package org.framework.helper;

import org.framework.utils.ClassUtil;

/**
 * Created by windy on 2016/9/20.
 */
public class HelperLoader {

    public static void init()
    {
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,IocHelper.class,ControllerHelper.class};
        for(Class<?> cls : classList)
        {
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
