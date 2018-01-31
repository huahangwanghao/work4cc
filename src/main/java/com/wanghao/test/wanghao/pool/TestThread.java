package com.wanghao.test.wanghao.pool;/**
 * Created by Administrator on 2018/1/30.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author WangH
 * @create 2018-01-30 10:47
 **/
public class TestThread {
    
    
    private Vector<User> users =new Vector<User>();
    private List<User> users1 =new ArrayList<User>();

    public static void main(String[] args) {

      TestThread tt=new TestThread();
      tt.test();
    //  tt.test1();
        
    }
    
    public void test(){
        for(int i=0;i<30;i++){
            users.addElement(new User(i+"",i+""));
        }

        for(int i=0;i<30;i++){
            final int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    users.get(finalI).name= finalI +""+"abc";
                }
            }.start();
        }
        System.out.println(users);
    }


    public void test1(){
        for(int i=0;i<30;i++){
            users1.add(new User(i+"",i+""));
        }

        for(int i=0;i<30;i++){
            final int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    users1.get(finalI).name= finalI +""+"abc";
                }
            }.start();
        }
        System.out.println(users1);
    }


    class User{
        public String id;
        public String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "id:"+id+",name"+name+" \n";
        }
    }
}
