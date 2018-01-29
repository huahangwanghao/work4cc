package com.wanghao.test.wanghao;

/**
 * 我们写了一个带有一个参数 value 的注解,这也是元数据注解的一种,
 * 参数的声明类型和接口的函数方式很相似 返回值指明了该参数的类型(这是是String),
 * <b>参数的声明是不能带任何参数的 比如 String value(int age) this is 错误的</b>
 * <br>Created by Administrator on 2018/1/26.
 */
public @interface OneParamAnnotation {
    
    String value();
    
}
