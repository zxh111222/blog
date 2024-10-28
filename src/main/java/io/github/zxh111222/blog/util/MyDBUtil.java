package io.github.zxh111222.blog.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBUtil {
    private static final DataSource dataSource;

    static {
        //1.引入依赖
        //2.提供配置文件
        //3.load 配置文件
        Properties prop = new Properties();
        try (InputStream inputStream = MyDBUtil.class.getClassLoader().getResourceAsStream("druid.properties")) {
            prop.load(inputStream);
            //4. 获取数据库连接池对象 DataSource
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection;
        try {
            //5. 获取数据库连接 Connection
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}