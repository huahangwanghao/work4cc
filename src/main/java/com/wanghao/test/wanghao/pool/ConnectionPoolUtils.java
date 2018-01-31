package com.wanghao.test.wanghao.pool;/**
 * Created by Administrator on 2018/1/30.
 */

/**
 * 连接池工具类
 *
 * @author WangH
 * @create 2018-01-30 9:50
 **/
public class ConnectionPoolUtils {
    /**
     *构造方法私有化
     */
    private ConnectionPoolUtils (){}
    
    
    private static MyConnectionPool pool=new MyConnectionPool("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            "root", "root");;
    
    public static MyConnectionPool getConnectionPool(){
       
        try {
            pool.createPool();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pool;
    }
    
    
}
