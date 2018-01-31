package com.wanghao.test.wanghao.pool;/**
 * Created by Administrator on 2018/1/29.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 我的连接池
 *
 * @author WangH
 * @create 2018-01-29 16:52
 **/
public class MyConnectionPool {
    
    private String jdbcDriver;
    private String dbUrl;
    private String userName;
    private String pwd;
    private String testTable;
    
    private int initConnectionCounts=10;
    private int autoIncrCount=5;
    private int maxConnectionCounts=50;
    
    private Vector<PooledConnection> connections;

    class  PooledConnection{
        Connection connection=null;
        boolean busy=false;

        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

    public MyConnectionPool(String jdbcDriver, String dbUrl, String userName, String pwd) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.pwd = pwd;
    }

    public String getTestTable() {
        return testTable;
    }

    public void setTestTable(String testTable) {
        this.testTable = testTable;
    }

    public int getInitConnectionCounts() {
        return initConnectionCounts;
    }

    public void setInitConnectionCounts(int initConnectionCounts) {
        this.initConnectionCounts = initConnectionCounts;
    }

    public int getAutoIncrCount() {
        return autoIncrCount;
    }

    public void setAutoIncrCount(int autoIncrCount) {
        this.autoIncrCount = autoIncrCount;
    }

    public int getMaxConnectionCounts() {
        return maxConnectionCounts;
    }

    public void setMaxConnectionCounts(int maxConnectionCounts) {
        this.maxConnectionCounts = maxConnectionCounts;
    }

    /**
     * 加了一个同步锁的概念,表示只能让一个线程进来
     * 创建一个连接池
     * @throws Exception
     */
    public synchronized  void createPool() throws Exception{
        
        if(connections!=null){
            return;
        }
        Driver driver= (Driver) Class.forName(jdbcDriver).newInstance();
        DriverManager.registerDriver(driver);
        connections=new Vector<PooledConnection>();
        createConnections(this.initConnectionCounts);
        
    }

    /**
     * 由 numConnections决定创建连接数量
     * @param numConnections
     * @throws Exception
     */
    private void createConnections (int numConnections) throws  Exception{
        for(int x=0;x<numConnections;x++){
            if(this.maxConnectionCounts>0&&this.connections.size()>=this.maxConnectionCounts){
                break;
            }

            connections.addElement(new PooledConnection(newConnection()));
        }
    }


    /**
     * 通过jdbc 创建一个 connection对象
     * @return
     * @throws Exception
     */
    private Connection newConnection()throws  Exception{
        Connection connection= DriverManager.getConnection(dbUrl,userName,pwd);
        
        if(connections.size()==0){
            //初始化
            DatabaseMetaData metaData=connection.getMetaData();
            int driverMaxConnectionCount=metaData.getMaxConnections();
            
            if(driverMaxConnectionCount>0&&this.maxConnectionCounts>driverMaxConnectionCount){
                this.setMaxConnectionCounts(driverMaxConnectionCount);
            }
            
        }
        
        
        return connection;
    }

    
    private  boolean testConnection(Connection connection){
        
        try {
            
            if(testTable.equals("")){
                connection.setAutoCommit(true);
            }else{
                connection.createStatement().execute("select count(0) from "+ testTable);
            }
            
        }catch (Exception e){
            closeConnection(connection);
            return false;
        }
        
        
        return false;
    }


    
    public synchronized  Connection getConnection()throws  Exception{
        
        if(connections==null){
            return null;
        }
        Connection connection=getFreeConnection();
        while(connection==null){
            wait(100);
            connection=getFreeConnection();
        }
        
        return connection;
        
    }
    
    
    /**
     * 返回一个数据库连接到连接池中,并把此连接置空,所有使用连接池获得数据库连接 均应该在不适用的情况下 返回连接池
     * @param connection
     */
    public void returnConnection(Connection connection){
        if(connections==null){
            System.out.println("连接池不存在,无法返回给连接池");
            return;
        }
        
        PooledConnection pConn;
        //给往回仍connection的时候,遍历所有的连接池内的连接,然后查看是否相等,如果相等就把状态设置为busy
        //因为在get的时候做了线程同步, 也就是说我得到的这个线程,别人肯定得不到
        Enumeration<PooledConnection> enumeration=connections.elements();
        while(enumeration.hasMoreElements()){
            pConn=enumeration.nextElement();
            if(connection==pConn.getConnection()){
                pConn.setBusy(false);
                break;
            }
        }
        
    }


    /**
     * 得到一个闲置的连接
     * @return
     * @throws Exception
     */
    private Connection getFreeConnection()throws  Exception{
        Connection connection=findFreeConnection();
        if(connection==null){
            createConnections(this.getAutoIncrCount());
            connection=findFreeConnection();
            if(connection==null){
                return null;
            }
        }
        return connection;
    }
    
    
    /**
     * 找到闲置的连接
     * @return
     */
    private Connection findFreeConnection() throws Exception{
        Connection conn=null;
        PooledConnection pConn;
        Enumeration<PooledConnection> enumerate = connections.elements();
        // 遍历所有的对象，看是否有可用的连接  
        while (enumerate.hasMoreElements()) {
            pConn=enumerate.nextElement();
            if(!pConn.isBusy()){
                conn=pConn.getConnection();
                pConn.setBusy(true);
                if(!testConnection(conn)){
                    //测试一下连接是否可用
                    try {
                        conn=newConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                    pConn.setConnection(conn);
                }
            }
            break;
        }
        return conn;
    }
    

    /**
     * 刷新连接池里面的所有连接对象
     * @throws Exception
     */
    public synchronized  void refreshConnections() throws Exception{
        if(connections==null){
            System.out.println("连接池不存在,无法关闭");
            return;
        }
        
        PooledConnection pConn;
        Enumeration<PooledConnection> enumeration=connections.elements();
        while (enumeration.hasMoreElements()){
            pConn=enumeration.nextElement();
            if(pConn.isBusy()){
                wait(100);
            }

            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnection());
            pConn.setBusy(false);
                
            }
        
        
    }
    

    /**
     * 关闭连接池里面的所有对象,并且清空连接池
     * @throws SQLException
     */
    public synchronized  void closeConnectionPool() throws  SQLException{
        
        if(connections==null){
            System.out.println("连接池不存在,无法关闭");    
            return;
        }
        
        PooledConnection pConnl;
        Enumeration<PooledConnection> enumeration=connections.elements();
        while (enumeration.hasMoreElements()){
            pConnl=enumeration.nextElement();
            if(pConnl.isBusy()){
                wait(5000);
            }
            closeConnection(pConnl.getConnection());
            connections.remove(pConnl);
        }
        //连接池置空
        connections=null;
    }
    
    

    /**
     * 关闭连接
     * @param connection
     */
    private void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 睡一会
     * @param mSenconds
     */
    private void wait(int mSenconds){
        try {
            Thread.sleep(mSenconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 加入同步锁,同时只能有一个线程访问,
     * 所有下面的B(),C() 没有必须加入锁啦.
     * 在B和C只有一个入口方法的时候,
     */
    public synchronized void A(){
        B();
    }

    private  void B() {
        C();
    }

    private  void C() {
        System.out.println("to do something");
    }


}
