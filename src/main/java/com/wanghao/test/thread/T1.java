package com.wanghao.test.thread; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年8月17日 下午4:10:44 
* 类说明 
*/
public class T1 {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
            T2 t2=new T2();
            t2.start();
            t2.join();
            System.out.println("T1执行完毕");
    }

}
 