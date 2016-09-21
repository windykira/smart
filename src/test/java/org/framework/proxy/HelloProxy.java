package org.framework.proxy;

import org.junit.Before;

/**
 * Created by windy on 2016/9/14.
 */
public class HelloProxy implements Hello{

    private Hello hello;

    public HelloProxy()
    {
        this.hello = new HelloImpl();
    }

    private void Before(){
        System.out.print("before...");
    }

    public void say() {
        Before();
        System.out.println("hello...");
    }
}
