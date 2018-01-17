package com.wanghao.test.thread;

import com.jcraft.jsch.jce.Random;

/**
 * @author WH 作者 E-mail:
 * @version 创建时间：2017年9月7日 下午3:55:45 类说明
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private int i;
    private static class InnerThread extends Thread {
        private ThreadLocalTest test;

        public InnerThread(ThreadLocalTest test) {
            this.test = test;
        }

        @Override
        public void run() {
          test.setValue(new java.util.Random().nextInt());
        }
    }

    //创建一个会有异常的场景
    
    public void setValue(int i){
        this.i=i;
    }
    
    
    
    
    public static void main(String[] args) {
       ThreadLocalTest t=new ThreadLocalTest();
       Thread t1=new InnerThread(t);
       Thread t2=new InnerThread(t);
       Thread t3=new InnerThread(t);
       t1.start();
       t2.start();
       t3.start();

    }

}
