package com.wanghao.test.wanghao.pool;/**
 * Created by Administrator on 2018/1/30.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 测试类
 *
 * @author WangH
 * @create 2018-01-30 9:56
 **/
public class ConnectionPoolTest {

    public static void main(String[] args) {
        
        for(int i=0;i<100;i++){
            new Thread(){
                @Override
                public void run() {
                    userPool();
                    //withOutPool();
                }
            }.start();

        }
        
    
        
       // withOutPool();
    }

    private static void withOutPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           
            String sql="select * from t_test";
            long startTime=System.currentTimeMillis();

            for(int i=0;i<100;i++){
                Connection connection= DriverManager.getConnection(  "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery(sql);
                while(rs.next()){
                    String id=rs.getString("t_id");
                    System.out.println("t_id is "+id);
                }
                rs.close();
                statement.close();
                //pool.returnConnection(connection);
                connection.close();
            }
            System.out.println("未经过经过100次的循环调用，使用连接池花费的时间:"+ (System.currentTimeMillis() - startTime) + "ms");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    

    private static void userPool(){
        

        try {

            MyConnectionPool pool=ConnectionPoolUtils.getConnectionPool();
            String sql="select * from t_test";
            long startTime=System.currentTimeMillis();

            for(int i=0;i<100;i++){
                Connection connection=pool.getConnection();
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery(sql);
                while(rs.next()){
                    String id=rs.getString("t_id");
                    System.out.println("t_id is "+id);
                }
                rs.close();
                statement.close();
                pool.returnConnection(connection);
            }
            System.out.println("经过100次的循环调用，使用连接池花费的时间:"+ (System.currentTimeMillis() - startTime) + "ms");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
