package com.wanghao.test.wanghao.aop.aop2;/**
 * Created by Administrator on 2018/1/31.
 */

/**
 * 主测试类
 * @author WangH
 * @create 2018-01-31 9:55
 **/
public class AOP2Main {

    public static void main(String[] args) {
        IHello hello= (IHello) new DynaProxyHello().bind(new Hello(),new DIYLogger());
       /* hello.sayHello("你好");
        hello.startNow();*/
       hello.getName();
       
       hello.saveUser();
        
    }
    
    
}
