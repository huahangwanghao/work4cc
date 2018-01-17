package com.wanghao.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class Test {

    // private final int a=10;
    // private final int b=10;
    // private final int c=11;
    // private final int d=11;
    private String s1 = "wang王浩";
    private String s2 = "wang王浩";
    private String s3 = "wang王浩";
    private Date date = new Date();

    private void t1() {
        System.out.println("test t");
    }

    public void g() {
        System.out.println("test g");
    }

    public static void main(String[] args) {
        /*try {
            int i = 1 / 0;

        } catch (Exception e) {
            System.out.println(getStackTraceInfo(e));
        }*/
        Student s=new Student();
        System.out.println("1"+s);
        s=new Student();
        System.out.println("2"+s);
        
    }
    
    public static String getStackTraceInfo(Exception e) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ex) {

            return "发生错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

    }
}