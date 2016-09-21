package org.framework.helper;

import org.framework.constant.ConfigConstant;
import org.framework.utils.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by windy on 2016/9/9.
 */
public class ConfigHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelper.class);
    private static final Properties prop = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取JDBC驱动
     * @return
     */
    public static String getJdbcDriver()
    {
        return PropsUtil.getString(prop,ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取应用基础包名
     */
    public static String getAppBasePackage()
    {
        return PropsUtil.getString(prop,ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用JSP路径
     */
    public static String getAppJspPath()
    {
        return PropsUtil.getString(prop,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
    }

    /**
     * 获取应用静态资源路径
     * @return
     */
    public static String getAppAssetPath()
    {
        return PropsUtil.getString(prop,ConfigConstant.APP_ASSET_PATH,"/asset/");
    }
}
