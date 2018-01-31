package com.wanghao.test.wanghao.aop.aop2;/**
 * Created by Administrator on 2018/1/31.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理实现
 *
 * @author WangH
 * @create 2018-01-31 9:51
 **/
public class DynaProxyHello implements InvocationHandler {
   
    private Object tagert;
    
    private Object proxy;
    
    public Object bind(Object tagert,Object proxy){
        this.tagert=tagert;
        this.proxy=proxy;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),this.tagert.getClass().getInterfaces(),this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        Object result=null;
       
        String methodName=method.getName();

        Class clzz=this.proxy.getClass();
        if(methodName.startsWith("get")){
            Method get=clzz.getDeclaredMethod("get",new Class[]{Method.class});
            get.invoke(this.proxy,method);
        }else if(methodName.startsWith("save")){
            Method save=clzz.getDeclaredMethod("save",new Class[]{Method.class});
            save.invoke(this.proxy,method);
        }
        result=method.invoke(this.tagert,args);

       


        return result;
    }
}
