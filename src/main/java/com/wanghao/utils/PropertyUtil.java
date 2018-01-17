package com.wanghao.utils;/**
 * Created by Administrator on 2018/1/17.
 */

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author WangH
 * @create 2018-01-17 16:39
 **/
public class PropertyUtil {
    
    private  static Map<String,String> map=null;
    
    public static String getPwd(String name){
        
        if(map==null){
            load();
        }
        return map.get(name);
        
    }

    

    public static void load() {
        System.out.println("init load()");
        map=new HashMap<String, String>();
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("name.properties");//new BufferedInputStream(new FileInputStream("classpath:name.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
               // System.out.println(key+":"+prop.getProperty(key));
                map.put(key,prop.getProperty(key));
            }
            in.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
     
        for(int i=0;i<10;i++){
            Thread.sleep(400);
            System.out.println(getPwd("admin1"));
        }
    }
}
