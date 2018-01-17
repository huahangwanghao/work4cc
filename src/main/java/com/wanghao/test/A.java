package com.wanghao.test; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年10月12日 上午11:20:49 
* 类说明 
*/

import java.math.BigDecimal;

public class A {

    private Integer age;
    
    private String name;
    
    private Long IdNo;
    
    private BigDecimal money;

    
    public A() {
        // TODO Auto-generated constructor stub
        this.age=1;
        this.name="jack";
        this.IdNo=123123123L;
        this.money=new BigDecimal("3.1415926");
    }
    
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdNo() {
        return IdNo;
    }

    public void setIdNo(Long idNo) {
        IdNo = idNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    
    
}
 