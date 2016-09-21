package org.framework.helper;

import org.framework.annotation.Inject;
import org.framework.utils.ArrayUtil;
import org.framework.utils.CollectionUtil;
import org.framework.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by windy on 2016/9/20.
 */
public class IocHelper {

    static {
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap))
        {
            Class<?> beanClass;
            Object beanInstance;
            Field[] beanFields;
            for(Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet())
            {
                beanClass = beanEntry.getKey();
                beanInstance = beanEntry.getValue();
                beanFields = beanClass.getFields();
                if(ArrayUtil.isNotEmpty(beanFields))
                {
                    for(Field beanField : beanFields)
                    {
                        if(beanField.isAnnotationPresent(Inject.class))
                        {
                            //Bean Map中获取Bean Field对应实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null)
                            {
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }


}
