package com.wanghao.test.wanghao.aop.aop1;/**
 * Created by Administrator on 2018/1/30.
 */

/**
 * 小狗的实现
 *
 * 这里是一个实现,没有任何问题
 * @author WangH
 * @create 2018-01-30 16:43
 **/
public class DogImpl implements AnimalInterface {
    
    private String name="dog";
    
    @Override
    public void setName(String name) {
        this.name=name;
        
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void say() {
        System.out.println("i am a dog   i can wawaaw");
    }

    @Override
    public void getProperty() {
        System.out.println("i can swim  i can run  i live in earth");
    }
}
