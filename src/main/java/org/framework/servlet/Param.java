package org.framework.servlet;

import org.framework.utils.CastUtil;

import java.util.Map;

/**
 * Created by windy on 2016/9/20.
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取所有字段信息
     */
    public Map<String,Object> getMap()
    {
        return paramMap;
    }

    public long getLong(String name)
    {
        return CastUtil.castLong(paramMap.get(name));
    }
}
