package com.bamboo.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @Author Bamboo
 * @Date 2020/11/14 16:44
 * @Version 1.0
 */
public class DataConnection {
    private static final String user;
    private static final String pwd;
    private static final String driver;
    private static final String url;
//    private static final Logger log= LoggerFactory.getLogger(DataConnection.class);

    private static Logger logger= LoggerFactory.getLogger(DataConnection.class);

    static {
        ResourceBundle resource = ResourceBundle.getBundle("properties");
        url=resource.getString("jdbc_url");
        user=resource.getString("jdbc_username");
        pwd=resource.getString("jdbc_password");
        driver=resource.getString("jdbc_driver");
    }
    public Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("数据库驱动未找到");
        }
        try {
            conn= DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            logger.error("密码或者用户名错误");
        }
        if (conn!=null){
            logger.info("数据库连接成功");

            return conn;
        }else {
            logger.error("连接失败");
            return null;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataConnection d=new DataConnection();
        d.getConnection();
    }

}
