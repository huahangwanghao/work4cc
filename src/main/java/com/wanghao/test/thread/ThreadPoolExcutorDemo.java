package com.wanghao.test.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年9月4日 下午2:25:33 
* 类说明 
*/
public class ThreadPoolExcutorDemo  extends ThreadPoolExecutor {

    public ThreadPoolExcutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(Runnable command) {
        // TODO Auto-generated method stub
    }
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
           String s="{\"key\":\"[{\"name\":123,\"age\":123},{\"name\":456,\"age\":465}]\"}";
    }

}
 