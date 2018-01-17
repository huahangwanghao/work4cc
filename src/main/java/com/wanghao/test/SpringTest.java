package com.wanghao.test;/**
 * Created by Administrator on 2017/10/12.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试spring源码的类
 *
 * @author WangH
 * @create 2017-10-12 10:04
 **/
public class SpringTest {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-core.xml");
        User user=ac.getBean(User.class);
        user.test();
        
    }
    
}
