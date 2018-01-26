package com.wanghao.listener;/**
 * Created by Administrator on 2018/1/25.
 */

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangH
 * @create 2018-01-25 15:55
 **/
public class RunTimeAspect {
    
    private Logger logger= LoggerFactory.getLogger(RunTimeAspect.class);

    /**
     * 
     * 前置通知
     * @param joinPoint
     */
    public void timeBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        logger.info("开始执行("+methodName+")的时候"+System.nanoTime());
        System.out.println("before");
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    public void timeAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        logger.info("执行完毕("+methodName+")的时候"+System.nanoTime());
    }
    
}
