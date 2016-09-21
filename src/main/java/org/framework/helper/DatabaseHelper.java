package org.framework.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.framework.utils.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by windy on 2016/9/19.
 */
public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
           LOGGER.error("can not load jdbc driver",e);
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection()
    {
        Connection connect = CONNECTION_HOLDER.get();
        if(connect == null)
        {
            try {
                connect = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(connect);
            }
        }
        return connect;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection()
    {
        Connection connect = CONNECTION_HOLDER.get();
        if(connect != null)
        {
            try {
                connect.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params)
    {
        List<T> entityList;
        return null;
    }
}
