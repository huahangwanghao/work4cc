package com.wanghao.test;

import java.math.BigDecimal;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年10月12日 上午11:21:37 
* 类说明 
*/
public class B {
  private Integer age;
    
    private String name;
    
    private Long IdNo;
    
    private String money;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "B [age=" + age + ", name=" + name + ", IdNo=" + IdNo + ", money=" + money + "]";
    }
    
    
    
}
 