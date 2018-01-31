package com.wanghao.test.wanghao.aop.aop2;/**
 * Created by Administrator on 2018/1/31.
 */

import java.lang.reflect.Method;

/**
 * @author WangH
 * @create 2018-01-31 9:54
 **/
public class DIYLogger implements ILogger {


    @Override
    public Object get(Method method) {

        System.out.println("this is aop get 不使用事务");
        
        return "get";
    }

    @Override
    public Object save(Method method) {
        System.out.println("this is aop save 使用事务");
        return "save";
    }
}
