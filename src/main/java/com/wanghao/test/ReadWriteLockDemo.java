package com.wanghao.test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WH 作者 E-mail:
 * @version 创建时间：2017年8月30日 上午9:17:38 类说明
 */
public class ReadWriteLockDemo {

    // 创建一个普通的锁
    private static Lock lock = new ReentrantLock();
    // 创建读写锁
    private static ReadWriteLock _readLock = new ReentrantReadWriteLock();
    private static ReadWriteLock _writeLock = new ReentrantReadWriteLock();
    // 得到读写锁
    private static Lock readLock = _readLock.readLock();
    private static Lock writeLock = _writeLock.writeLock();
    private int value;

    /**
     * 模拟读请求
     * 
     * @param lock
     * @return
     * @throws Exception
     */
    public Object handleRead(Lock lock) throws Exception {
        try {
            lock.lock(); // 获取锁
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }
    /**
     * 模拟写操作
     * @param lock
     * @param value
     * @throws Exception
     */
    public void handleWrite(Lock lock,Integer value)  throws Exception{
        try {
            lock.lock();
            Thread.sleep(1000);   //当前调用它的线程sleep
            this.value=value;
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Date());
        final ReadWriteLockDemo demo=new ReadWriteLockDemo();
        //这个是使用了匿名类的方式创建一个类
        Runnable readRunnable=new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("read--start"+new Date());
                    demo.handleRead(readLock);
                    //demo.handleRead(lock);
                    System.out.println("read--end"+new Date());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        };
        
        Runnable writeRunnable=new Runnable() {
            @Override
            public void run() {
             try {
                 System.out.println("write--start"+new Date());
                demo.handleWrite(writeLock, 1);
                //demo.handleRead(lock);
                System.out.println("write--end"+new Date());
            } catch (Exception e) {
               e.printStackTrace();
            }
            }
        };
        
       for (int i = 0; i < 18; i++) {
        new Thread(readRunnable).start();
       }
       for (int i = 18; i < 20; i++) {
           new Thread(writeRunnable).start();
          }
       System.out.println(new Date());
    }

}
