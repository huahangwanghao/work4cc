package com.wanghao;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年6月20日 下午5:58:58 
* 类说明 
*/
public class Main {

    public static void main(String[] args) {
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = Main.class.getClassLoader().getResourceAsStream("name.properties");//new BufferedInputStream(new FileInputStream("classpath:name.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();

           /* ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();*/
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
 