package org.framework.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by windy on 2016/9/20.
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 数据模型
     */
    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        this.model = new HashMap();
    }

    public View addModel(String key,Object value)
    {
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
