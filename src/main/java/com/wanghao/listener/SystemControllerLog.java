package com.wanghao.listener;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年3月2日 上午11:20:18 
* 类说明 
*/
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface SystemControllerLog {    
    
    String description()  default "";    
    
    
} 