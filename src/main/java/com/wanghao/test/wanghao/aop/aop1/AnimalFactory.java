package com.wanghao.test.wanghao.aop.aop1;/**
 * Created by Administrator on 2018/1/30.
 */

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对象工厂
 * @author WangH
 * @create 2018-01-30 16:51
 **/
public class AnimalFactory<T> {
    
    
    private static Object getAnimalBase(Object obj){
        //得到一个代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new AOPHandle(obj, new AOPMethod() {
            @Override
            public void after(Object proxy, Method method, Object[] args) {
                System.out.println("i run  after "+method.getName());
            }

            @Override
            public void before(Object proxy, Method method, Object[] args) {
                System.out.println("i run  before "+method.getName());
            }
        }));
    }
    
    
    public static Object getAnimal(Object obj){
        return getAnimalBase(obj);
    }

    @SuppressWarnings("unchecked")
    public  static Object getAnimal(String className){
        Object obj=null;
        try {
            obj= getAnimalBase(Class.forName(className).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    /***
     * 获取对象方法
     * @param clz
     * @return
     */
    @SuppressWarnings("unchecked")
    public  static Object  getAnimal(Class clz){
        Object obj=null;
        try {
            obj= getAnimalBase(clz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    
}
