package com.wanghao.test; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年7月6日 下午1:57:59 
* 类说明 
*/
public class MyThread extends Thread {

    private ThreadLocal<Integer> threadLocal;
    
    public MyThread(ThreadLocal<Integer> t) {
        this.threadLocal=t;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        threadLocal.set(0);
        for(int i=0;i<100;i++){
            int m=threadLocal.get();
            threadLocal.set(m+1);
        }
        System.out.println(threadLocal.get());
        
    }
    
    public static void main(String[] args) {
        
        ThreadLocal<Integer> tl=new ThreadLocal<Integer>();
        new MyThread(tl).start();
        new MyThread(tl).start();
        
        
        
    }
}
 