package com.wanghao.test.thread; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年9月7日 下午6:33:43 
* 类说明 
*/
public final class NoChange {

    private final String name;
    
    public NoChange(String name) {
        this.name=name;
    }
    
    
    public String getName() {
        return name;
    }


    public static void main(String[] args) {
       NoChange nc=new NoChange("1");
       nc=new NoChange("2");

    }

}
 