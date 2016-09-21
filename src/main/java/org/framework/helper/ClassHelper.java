package org.framework.helper;

import org.framework.annotation.Controller;
import org.framework.annotation.Service;
import org.framework.utils.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by windy on 2016/9/20.
 */
public class ClassHelper {

    /**
     * 定义类集合（用于存放所加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下所有类
     * @return
     */
    public static Set<Class<?>> getClassSet()
    {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有Service类
     */
    public static Set<Class<?>> getServiceClassSet()
    {
        Set<Class<?>> classSet = new HashSet();
        for(Class<?> cls : CLASS_SET)
        {
            if(cls.isAnnotationPresent(Service.class))
            {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet()
    {
        Set<Class<?>> classSet = new HashSet();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有Bean类（Controller、Service等）
     */
    public static Set<Class<?>> getBeanClassSet()
    {
        Set<Class<?>> classSet = new HashSet();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
