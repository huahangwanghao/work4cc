package com.wanghao.test.wanghao.aop.aop1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 把aophandler接口,声明成一个动态代理的类
 * 这个是重点了哈:
 * 第一步实现invocationHandler接口
 * 里面让Object o,这个是代理对象, 通过构造方法传递进来的.
 * 
 * 
 * @author WangH
 * @create 2018-01-30 16:45
 **/
public class AOPHandle implements InvocationHandler {
    //代理对象
    private Object o;
   // 
    private AOPMethod aopMethod;

    public AOPHandle(Object o,AOPMethod aopMethod) {
        this.o = o;
        this.aopMethod=aopMethod;
    }

    /**
     * 这个方法,会自动调用java动态代理机制,
     * @param proxy  代理对象的接口,
     * @param method  被调用的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        Object ret=null;

        //System.out.println("执行方法是:"+method.getName()+"  参数类型是:\n");
        
        for(Class type:method.getParameterTypes()){
            System.out.println(type.getName());
        }

        //System.out.println("返回类型是:"+method.getReturnType().getName());
        //返回值
        this.aopMethod.before(proxy,method,args);
        //就是执行 目标对象o里面的method 这个方法, 参数就是args
        ret=method.invoke(o,args);
        this.aopMethod.after(proxy,method,args);
        System.out.println("方法执行完毕!!!");
        
        return ret;
    }
}
