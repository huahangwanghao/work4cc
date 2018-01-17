package com.wanghao.controller;/**
 * Created by Administrator on 2018/1/13.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取txt文件
 *
 * @author WangH
 * @create 2018-01-13 17:25
 **/
public class Main {



    private static  void createFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                File f=file.getParentFile();
                f.mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public  static void main(String args[]) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  
            createFile("D://admin//3.xlsx");
           if(true){
               return;
           }
                /* 读入TXT文件 */
            String pathname = "D:\\1.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename = new File(pathname); // 要读取以上路径的input。txt文件  
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";
            line = br.readLine();
           
           /* while (line != null) {
                line = br.readLine(); // 一次读入一行数据  
               *//* int index=line.indexOf("客户姓名");
                System.out.println(index);*//*
            }*/
            int index=line.indexOf("客户姓名");
            String newLine=line.substring(index,line.indexOf("合计项目"));
            newLine=newLine.replaceAll("是否包含学位 ","是否包含学位 \n");
            newLine=newLine.replaceAll(" 否 "," 否 \n");
            newLine=newLine.replaceAll(" 是 "," 是 \n");
            
           // System.out.println(newLine);

                /* 写入Txt文件 */
            File writename = new File("D:\\2.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
            writename.createNewFile(); // 创建新文件  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(newLine); // \r\n即为换行  
            out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件  

            String pathname2 = "D:\\2.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename2 = new File(pathname2); // 要读取以上路径的input。txt文件  
            InputStreamReader reader2 = new InputStreamReader(
                    new FileInputStream(filename2)); // 建立一个输入流对象reader  
            BufferedReader br2 = new BufferedReader(reader2); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line2 = "";
            line2 = br2.readLine();
            System.out.println(line2);
            List<StudentInfo> list=new ArrayList<StudentInfo>();
            while (line2 != null) {
                line2 = br2.readLine();
                if(line2!=null&&line2.contains("客户姓名 ")){
                    continue;
                }
                System.out.println(line2);
                if(line2!=null)
                list.add(getStudetInfo(line2));
            }
            
            WriteExcel.writeExcel(list,20,"D://1.xlsx");
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static StudentInfo getStudetInfo(String str1){
        StudentInfo studentInfo=new StudentInfo();
        String [] s=str1.split(" ");
        int i=0;
        String name=s[0];
        studentInfo.setName(name);
        String sex=s[1];
        if("男".equals(sex)|| "女".equals(sex)){
            studentInfo.setSex(sex);
        }else{
            studentInfo.setSex("无性别");
        }

        String phone=s[2];

        studentInfo.setPhone(phone);
        String guanlianPhone=s[3];
        if(guanlianPhone.length()<=1){
            studentInfo.setGuanlianPhone("关联手机号无");
        }else{
            studentInfo.setGuanlianPhone(guanlianPhone);
        }
        String jinJiLianxiRen=s[4];

        if(jinJiLianxiRen.length()!=0&&!jinJiLianxiRen.startsWith("201")&&!isMobileNO(jinJiLianxiRen)){
            if(isContainMobileNO(jinJiLianxiRen)){
                //表名是  张三1817123123
                studentInfo.setJinJiLianxiRen(jinJiLianxiRen);
                i=4;
                        
            }else{
                studentInfo.setJinJiLianxiRen(jinJiLianxiRen+s[5]);
                i=5;
            }
            
            
        }else if(isMobileNO(jinJiLianxiRen)){
            studentInfo.setGuanlianPhone(jinJiLianxiRen);
            studentInfo.setJinJiLianxiRen("紧急联系人无");
            i=4;
        }else{
            studentInfo.setJinJiLianxiRen("紧急联系人无");
            i=4;
        }
        String createDate=s[++i];
        if(createDate.startsWith("201")){
            studentInfo.setCreateDate(createDate+" "+s[++i]);
        }
        String zhengjianLeiXing=s[++i];

        studentInfo.setZhengJianType(zhengjianLeiXing);

        String zhengjianNo=s[++i];
        studentInfo.setZhengJianNo(zhengjianNo);

        String beizhu=s[++i];
        int x=i;
        if(beizhu.length()!=0){
            studentInfo.setBeizhu(beizhu);
        }else{
            studentInfo.setBeizhu("无备注");
        }

        String email=s[++i];
        if(isEmail(email)){
            studentInfo.setEmail(email);    
        }else{
            //如果不是email
            int j=i;
            for(int m=1;i<100;m++){
               if(isEmail(s[j+m])){
                   studentInfo.setEmail(s[j+m]);       
                   i=j+m;
                   int xx=i;
                   StringBuilder sb =new StringBuilder();
                   for(int g=x;g<xx;g++){
                       sb.append(s[g]);
                   }
                   studentInfo.setBeizhu(sb.toString());
                   break;
               }
            }
        }
        

        String jiBie=s[++i];
        studentInfo.setJiBie(jiBie);

        String banJi=s[++i];
        studentInfo.setBanji(banJi);
        String baoKaoYuanxiao=s[++i];
        studentInfo.setBaoKaoYuanxiao(baoKaoYuanxiao);
        String baoKaoZhuanYe=s[++i];
        studentInfo.setBaoKaoZhuanYe(baoKaoZhuanYe);
        String yingJiaoFei=s[++i];
        studentInfo.setYingJiaoXueFei(yingJiaoFei);
        String shiJiJiaoFei=s[++i];
        studentInfo.setShiJiaoXueFei(shiJiJiaoFei);
        String zhifuFangShi=s[++i];
        studentInfo.setZhiFuFangShi(zhifuFangShi);
        String xuezhi=s[++i];
        studentInfo.setXueZhi(xuezhi);
        String shifouBaoHanXueWei=s[++i];
        studentInfo.setShiFouBaoHanXueWei(shifouBaoHanXueWei);
        return studentInfo;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(19[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return  matcher.matches();
    }


    public static boolean isContainMobileNO(String mobiles) {
        
        return mobiles.contains("1");
        
    }
}
