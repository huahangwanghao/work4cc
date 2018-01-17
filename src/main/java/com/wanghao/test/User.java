package com.wanghao.test;
/**
 * Created by Administrator on 2017/10/12.
 */

/**
 * spring测试的user类
 *
 * @author WangH
 * @create 2017-10-12 10:05
 **/
public class User {
    
    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void test(){
        System.out.println("this is user test");
    }
}
