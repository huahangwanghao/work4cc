package com.wanghao.test.wanghao.aop.aop1;/**
 * Created by Administrator on 2018/1/30.
 */

/**
 * 这里是主函数,没有任何问题
 * @author WangH
 * @create 2018-01-30 16:55
 **/
public class AopMain {

    public static void main(String[] args) {
        //下面这个dog其实是代理对象 dog就是代理对象
        AnimalInterface  dog= (AnimalInterface ) AnimalFactory.getAnimal(DogImpl.class);
        //通过代理对象去调用方法的时候, 会走到invoke里面
       dog.say();
        System.out.println("my name is "+dog.getName());
        dog.setName("good dog");
        System.out.println("my name is "+dog.getName());
    }
    
}
