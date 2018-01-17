package com.wanghao.test.thread; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年8月17日 下午4:11:03 
* 类说明 
*/
public class T2 extends Thread {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("T2之行开始");
            Thread.sleep(1000);
            System.out.println("T2之行结束");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.run();
    }
}
 