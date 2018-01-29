package com.wanghao.test.wanghao;/**
 * Created by Administrator on 2018/1/26.
 */

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author WangH
 * @create 2018-01-26 15:10
 **/
public class MethodTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        
        MethodTest test=new MethodTest();
        Class clzz=test.getClass();
        Method method=clzz.getMethods()[1];
        String name=method.getName();
        System.out.println("method name is "+name);
        Class [] classes=method.getParameterTypes();
        for(Class cla:classes){
            System.out.println("参数类型:"+cla.getName());
        }

        Parameter[] parameters= method.getParameters();
        for(Parameter parameter:parameters){
            System.out.println("参数名称:"+parameter.getName());
            Annotation[] annotations=parameter.getAnnotations();
            for(Annotation annotation:annotations){
                Colume colume= (Colume) annotation;
                System.out.println("参数注解:"+(parameter).isAnnotationPresent(Colume.class));
                System.out.println("参数注解的值是 vlaue is="+colume.value());
                //前端可以获取到id的是是 10
                String id=10+"";
                boolean isVar=parameter.isVarArgs();
                method.invoke(new MethodTest(),id,1);
                System.out.println("参数值是否是变量:"+isVar);
            }
        }
        
        
    }
    
    public Integer test(@Colume("id") String age, Integer name){
        System.out.println("age---------------"+age);
        return 1;
    }
    
    
}
