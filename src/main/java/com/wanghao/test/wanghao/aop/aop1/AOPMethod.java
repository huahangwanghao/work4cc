package com.wanghao.test.wanghao.aop.aop1;/**
 * Created by Administrator on 2018/1/30.
 */

import java.lang.reflect.Method;

/**
 * 这里是创建一个接口,没有任何问题
 * @author WangH
 * @create 2018-01-30 17:03
 **/
public interface AOPMethod {
    
    //方法执行之后
    void after(Object proxy, Method method, Object[] args);
    //方法执行之前
    void before(Object proxy, Method method, Object[] args);
    
}
