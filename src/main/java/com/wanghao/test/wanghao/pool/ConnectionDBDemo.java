package com.wanghao.test.wanghao.pool;/**
 * Created by Administrator on 2018/1/29.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author WangH
 * @create 2018-01-29 10:36
 **/
public class ConnectionDBDemo {

    public static void main(String[] args) throws Exception {
        
        //第一步注册驱动
        Class.forName("com.wanghao.jdbc");
        //第二部连接
        Connection connection=DriverManager.getConnection("","","");
        //第三部
        PreparedStatement ps=connection.prepareStatement("select * from user");
        //第四部返回
        ResultSet rs=ps.executeQuery();
        
        
        rs.close();
        ps.close();
        connection.close();
        
    }
    
}
