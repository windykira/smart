package org.framework.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by windy on 2016/9/14.
 */
public class ProxyTest {

    @Test
    public void test()
    {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        /*Hello proxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy
        );*/
        Hello proxy = dynamicProxy.getProxy();
        proxy.say();
    }
}
