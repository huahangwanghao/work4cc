package com.wanghao.test.wanghao.aop.aop2;/**
 * Created by Administrator on 2018/1/31.
 */

/**
 * @author WangH
 * @create 2018-01-31 9:50
 **/
public class Hello implements IHello {

    @Override
    public void sayHello(String str) {
        System.out.println("hello to you "+str);
    }

    @Override
    public void startNow() {
        System.out.println("this is  startNow method");
    }

    @Override
    public void getName() {
        
    }

    @Override
    public void saveUser() {

    }


}
