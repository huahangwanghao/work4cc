package com.wanghao.listener; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年3月2日 上午11:20:58 
* 类说明 
*/
import java.lang.annotation.*;    

/**  
 *自定义注解 拦截service  
 */    
    
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface SystemServiceLog {    
    
    String description()  default "";    
    
    
}   