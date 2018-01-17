package com.wanghao;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年6月20日 下午5:58:58 
* 类说明 
*/
public class Main {
    private static HSSFWorkbook workbook = null;

    public static void main(String[] args)throws Exception {
     /*   Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("D://a/a.xlsx");
        wb.write(fileOut);
        fileOut.close();
*/String title[] = {"id1","name1","password"};
        Main.createExcel("E:/test2.xls","sheet1");
    }

    public static boolean fileExist(String fileDir){
        boolean flag = false;
        File file = new File(fileDir);
        flag = file.exists();
        return flag;
    }

    public static boolean sheetExist(String fileDir,String sheetName) throws Exception{
        boolean flag = false;
        File file = new File(fileDir);
        if(file.exists()){    //文件存在
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if(sheet!=null)
                    flag = true;
            } catch (Exception e) {
                throw e;
            }

        }else{    //文件不存在
            flag = false;
        }
        return flag;
    }

    public static void createExcel(String fileDir,String sheetName) throws Exception{
        //创建workbook
        workbook = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFSheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头
            HSSFRow row = workbook.getSheet(sheetName).createRow(0);    //创建第一行

            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
 