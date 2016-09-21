package org.framework.servlet;

/**
 * Created by windy on 2016/9/20.
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel()
    {
        return model;
    }
}
