package com.wanghao.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年10月12日 上午11:23:13 
* 类说明 
*/
public class ChangeTest {

    
    public static void main(String[] args) {
        //模拟商户系统返回的数据
        All all=new All();
        all.setCode(0);
        all.setMsg("成功");
        List<A> list=new ArrayList<A>();
        list.add(new A());
        all.setData(list);
        List<B> listB=new ArrayList<B>();
        
        //类之间的
        A2B(all.getData(),listB);
        //listB就是返回的结果
        System.out.println(listB);
        
        
    }
    
    /**
     * 对象之间的转换关系
     * @param listA
     * @param listB
     * @return
     */
    private static List<B> A2B(List<A> listA,List<B> listB){
        for(A a:listA){
            B b =new B();
            b.setAge(a.getAge());
            b.setName(a.getName());
            b.setIdNo(a.getIdNo());
            a.getMoney();
            BigDecimal bd=a.getMoney().setScale(2,BigDecimal.ROUND_HALF_UP);
            b.setMoney(bd.toString());
            listB.add(b);
        }
        return listB;
    }
    
}
 