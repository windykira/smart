package org.framework.helper;

import org.framework.annotation.Action;
import org.framework.handler.Handler;
import org.framework.handler.Request;
import org.framework.utils.ArrayUtil;
import org.framework.utils.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by windy on 2016/9/20.
 */
public class ControllerHelper {

    private static final Map<Request,Handler> ACTION_MAP = new HashMap();

    static {
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet))
        {
            Method[] methods;
            Action action;
            String mapping;
            String[] array;
            String requestMethod;
            String requestPath;
            Request request;
            Handler handler;
            for(Class<?> controllerClass : controllerClassSet)
            {
                //获取controller类中定义的方法
                methods = controllerClass.getMethods();
                if(ArrayUtil.isNotEmpty(methods))
                {
                    for(Method method : methods)
                    {
                        //判断方法是否带有Action注解
                        if(method.isAnnotationPresent(Action.class))
                        {
                            //从Action注解中获取URL映射规则
                            action = method.getAnnotation(Action.class);
                            mapping = action.value();
                            //验证URL映射规则
                             if(mapping.matches("\\w+:/\\w*"))
                             {
                                array = mapping.split(":");
                                 if(ArrayUtil.isNotEmpty(array) && array.length == 2)
                                 {
                                     //获取请求方法与请求路径
                                     requestMethod = array[0];
                                     requestPath = array[1];
                                     request = new Request(requestMethod,requestPath);
                                     handler = new Handler(controllerClass,method);
                                     //初始化ACTION MAP
                                     ACTION_MAP.put(request,handler);
                                 }
                             }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     */
    public static Handler getHandler(String requestMethod,String requestPath)
    {
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}
