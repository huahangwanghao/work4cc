package com.wanghao.test.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author WH 作者 E-mail:
 * @version 创建时间：2017年8月31日 上午10:30:15 类说明
 */
public class StopThreadUnsafe {
    private static ReentrantLock lock=new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static ReadLock readLock=readWriteLock.readLock();
    private static WriteLock writeLock=readWriteLock.writeLock();
    private int i;
  
    
    private int getValue() throws Exception{
        try {
            lock.lock();
            Thread.sleep(1000);
            return i;
        } finally {
            lock.unlock();
        }
    }
    
    private void setValue(int i){
        try {
            writeLock.lock();
            this.i=i;    
        } finally {
            writeLock.unlock();
        }
        
    }
    
    
     
    
    
    public static void main(String[] args) throws Exception{
        final StopThreadUnsafe s=new StopThreadUnsafe();
        Runnable read=new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(s.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        Runnable write=new Runnable() {
            
            @Override
            public void run() {
               
                s.setValue(new Random(1000).nextInt());
            }
        };
        for (int i = 0; i < 18; i++) {
            new Thread(read).start();
        }
        for(int j=18;j<20;j++){
            new Thread(write).start();
        }
    }
    
    
    
}
