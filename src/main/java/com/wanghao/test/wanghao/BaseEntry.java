package com.wanghao.test.wanghao;/**
 * Created by Administrator on 2018/1/26.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 基本类
 *
 * @author WangH
 * @create 2018-01-26 14:18
 **/
@Table("user")
public class BaseEntry {
    
    //@Colume("age")
    private int age1=10;
    
    public void test(){
        
        String showMessage="";
        
        Class entryClass=this.getClass();

        boolean isTable=entryClass.isAnnotationPresent(Table.class);
        if(isTable){
            Table table= (Table) entryClass.getAnnotation(Table.class);
            showMessage +="Table : "+table.value()+"\n";
        }
        Method method=null;
       /* Parameter[] parameters=method.getParameters();*/
       
        
        
        Field[] fields=entryClass.getDeclaredFields();
        for(Field field:fields){
            boolean isColume=field.isAnnotationPresent(Colume.class);
            if(isColume){
                Colume colume=field.getAnnotation(Colume.class);
                showMessage +="Colume :"+colume.value();
                
                
                Object value="";
                
                field.setAccessible(true);

                try {
                    value=field.get(this);
                    showMessage+= ";value is "+value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        System.out.println(showMessage);
        
        
    }


    public static void main(String[] args) {
        BaseEntry baseEntry=new BaseEntry();
        baseEntry.test();
    }
}
