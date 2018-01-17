package com.wanghao.test.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年9月7日 下午4:05:27 
* 类说明 
*/
public class ThreadLocalDemo1 {
   // private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> threadLocall_sdf=new ThreadLocal<SimpleDateFormat>();
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i) {
            this.i=i;
        }
        @Override
        public void run() {
            try {
                
                
                SimpleDateFormat sdf=threadLocall_sdf.get();//因为使用了线程池,所有每个线程中的threadLocal就不会消失了
                if(sdf==null){
                    threadLocall_sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    sdf=threadLocall_sdf.get();
                }
                    Date d=sdf.parse("2017-09-03 19:19:"+i%60);
                    System.out.println(Thread.currentThread().getId()+""+d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void main(String[] args) {
       Executor executor=Executors.newFixedThreadPool(6);
       for(int i=0;i<1000;i++){
           executor.execute(new ParseDate(i));
       }

    }

}
 