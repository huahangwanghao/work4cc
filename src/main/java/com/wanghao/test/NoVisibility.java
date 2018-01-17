package com.wanghao.test; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年7月6日 上午11:03:55 
* 类说明 
*/
public class NoVisibility {
    private static boolean ready;
         private static int number;
         private static class ReaderThread extends Thread {
             @Override
             public void run() {
                while(!ready) {
                    Thread.yield();
                }
                System.out.println(number);
            }
        }
        public static void main(String[] args) throws Exception {
            System.out.println(System.currentTimeMillis());
            new ReaderThread().start();
            new ReaderThread().start();
            new ReaderThread().start();
            new ReaderThread().start();
            new ReaderThread().start();
            Thread.sleep(1000);
           number = 42;
           ready = true;
           System.out.println(System.currentTimeMillis());
       }
}
 