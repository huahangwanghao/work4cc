package com.wanghao.test.wanghao.aop.aop2;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/1/31.
 */
public interface ILogger {
    
   Object get(Method method);
    
   Object save(Method method);
   
}
