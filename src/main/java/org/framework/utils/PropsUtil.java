package org.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by windy on 2016/9/19.
 */
public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName)
    {
        Properties prop = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is == null)
            {
                throw new FileNotFoundException(fileName + " is not found");
            }
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure");
                }
            }
        }
        return prop;
    }

    /**
     * 获取字符型属性（默认值为空字符串）
     * @param prop
     * @param key
     * @return
     */
    public static String getString(Properties prop,String key)
    {
        return PropsUtil.getString(prop,key,"");
    }

    /**
     * 获取字符型属性（可指定默认值）
     * @param prop
     * @param key
     * @return
     */
    public static String getString(Properties prop,String key,String defaultValue)
    {
        String value = defaultValue;
        if(prop.contains(key))
        {
            value = prop.getProperty(key);
        }
        return value;
    }
}
