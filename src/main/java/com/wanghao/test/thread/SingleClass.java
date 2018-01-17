package com.wanghao.test.thread; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年9月7日 下午6:24:41 
* 类说明 
*/
public class SingleClass {

    public static String name="abc";
    private static class SingleClassHolder{
        public static SingleClass sc=new SingleClass();
    }
    private SingleClass(){}//构造方法私有化
    public static  SingleClass getInstance(){ //1.具有懒加载的特性,不调用不创建,2.不存在锁竞争问题 
        return SingleClassHolder.sc;
    }
}
 