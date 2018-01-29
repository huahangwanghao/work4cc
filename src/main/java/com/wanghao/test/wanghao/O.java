package com.wanghao.test.wanghao;/**
 * Created by Administrator on 2018/1/26.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 算法的描述
 *
 * @author WangH
 * @create 2018-01-26 10:56
 **/
public class O {
    
    @RunTimeAnnotation
    private String name;
    
    @FieldAnnotation
    private String id;

    @MethodAnnotation
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
       O o=new O();
       o.testRetentionAnnotation();
    }
    
    public  void testRetentionAnnotation(){
        Class clzz=this.getClass();   
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            //表示该 filed 是否被某个 Annotation 修饰
            if(field.isAnnotationPresent(RunTimeAnnotation.class)){
                System.out.println("我是被这个注解修饰啦~~");
            }
        }
    }

    /**
     * 测试方法注解
     */
    public  void testMethodAnnotation(){
        Class clzz=this.getClass();
        Method[] methods=clzz.getMethods();
    }
    /**
     * 测试属性在注解
     */
    public  void testFieldAnnotation(){
        Class clzz=this.getClass();
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            //表示该 Target 是否被某个 Annotation 修饰
            if(field.isAnnotationPresent(RunTimeAnnotation.class)){
                System.out.println("我是被这个注解修饰啦~~");
            }
        }
    }
    
   
}
