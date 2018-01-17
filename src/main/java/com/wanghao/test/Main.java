package com.wanghao.test;

import java.math.BigDecimal;


/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年3月2日 下午5:55:19 
* 类说明 
*/
public class Main extends Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /*ApplicationContext ac=new ClassPathXmlApplicationContext("application.xml");
        Product p= ac.getBean("product",Product.class);
        System.out.println(p.getClass());*/
        t();
        
        Main main=new Main();
       
        
        BigDecimal a =new BigDecimal("1.0123");
        System.out.println(a.toString());
        
        
    }
    
    public static String t(){
        try {
            System.out.println("try");
            return "1";
        } catch (Exception e) {
            System.out.println("catch");
            // TODO: handle exception
            return "2";
        }finally {
            System.out.println("finally");
        }
      
        
    }

}
 