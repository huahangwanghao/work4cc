package com.wanghao.controller;/**
 * Created by Administrator on 2018/1/15.
 */


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author WangH
 * @create 2018-01-15 12:22
 **/
public class WriteExcel {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeExcel(List<StudentInfo> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数  
            int columnNumCount = cloumnCount;
            // 读取Excel文档  
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页  
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列 
             */
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算  
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效  
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据 
             */
            for (int j = 0; j < dataList.size(); j++) {
                
                if(j==0){
                    Row row = sheet.createRow(j);    
                    row.createCell(0).setCellValue("客户姓名");
                    row.createCell(1).setCellValue("性别");
                    row.createCell(2).setCellValue("手机号码");
                    row.createCell(3).setCellValue("管理手机号码");
                    row.createCell(4).setCellValue("紧急联系人及号码"); 
                    row.createCell(5).setCellValue("创建时间");
                    row.createCell(6).setCellValue("证件类型");
                    row.createCell(7).setCellValue("证件号码"); 
                    row.createCell(8).setCellValue("备注");
                    row.createCell(9).setCellValue("邮箱");
                    row.createCell(10).setCellValue("级别");
                    row.createCell(11).setCellValue("班级");
                    row.createCell(12).setCellValue("报考院校");
                    row.createCell(13).setCellValue("报考专业");
                    row.createCell(14).setCellValue("应缴学费");
                    row.createCell(15).setCellValue("实缴学费");
                    row.createCell(16).setCellValue("支付方式");
                    row.createCell(17).setCellValue("几年学制");
                    row.createCell(18).setCellValue("是否包含学位");
                    
                    
                    
                    
                }
                
                // 创建一行：从第二行开始，跳过属性列  
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录  
                StudentInfo st = dataList.get(j);
                String name = st.getName();
                String address = st.getSex();
                String phone = st.getPhone();
                String guanlianshoujihao=st.getGuanlianPhone();
                String jinJilianxiren=st.getJinJiLianxiRen();
                String createDate=st.getCreateDate();
                String zhengjianType=st.getZhengJianType();
                String zhengJianNo=st.getZhengJianNo();
                String beizhu=st.getBeizhu();
                String email=st.getEmail();
                String jibie=st.getJiBie();
                String banji=st.getBanji();
                String baokaoyuanxiao=st.getBaoKaoYuanxiao();
                String baokaozhuanye=st.getBaoKaoZhuanYe();
                String yingjixuefei=st.getYingJiaoXueFei();
                String shijixuefei=st.getShiJiaoXueFei();
                String zhifufangshi=st.getZhiFuFangShi();
                String xuezhi=st.getXueZhi();
                String shifoubh=st.getShiFouBaoHanXueWei();
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环  
                    Cell first = row.createCell(0);
                    first.setCellValue(name);

                    Cell second = row.createCell(1);
                    second.setCellValue(address);

                    Cell third = row.createCell(2);
                    third.setCellValue(phone);

                    Cell cell3 = row.createCell(3);
                    cell3.setCellValue(guanlianshoujihao);
                    Cell cell4 = row.createCell(4);
                    cell4.setCellValue(jinJilianxiren);
                    Cell cell5 = row.createCell(5);
                    cell5.setCellValue(createDate);
                    Cell cell6 = row.createCell(6);
                    cell6.setCellValue(zhengjianType);
                    Cell cell7 = row.createCell(7);
                    cell7.setCellValue(zhengJianNo);
                    Cell cell8 = row.createCell(8);
                    cell8.setCellValue(beizhu);
                    Cell cell9 = row.createCell(9);
                    cell9.setCellValue(email);
                    Cell cell10 = row.createCell(10);
                    cell10.setCellValue(jibie);
                    Cell cell11 = row.createCell(11);
                    cell11.setCellValue(banji);
                    Cell cell12 = row.createCell(12);
                    cell12.setCellValue(baokaoyuanxiao);
                    Cell cell13 = row.createCell(13);
                    cell13.setCellValue(baokaozhuanye);
                    Cell cell14 = row.createCell(14);
                    cell14.setCellValue(yingjixuefei);
                    Cell cell15 = row.createCell(15);
                    cell15.setCellValue(shijixuefei);
                    Cell cell16 = row.createCell(16);
                    cell16.setCellValue(zhifufangshi);
                    Cell cell17 = row.createCell(17);
                    cell17.setCellValue(xuezhi);
                    Cell cell18 = row.createCell(18);
                    cell18.setCellValue(shifoubh);
                   /* Cell cell19 = row.createCell(19);
                    cell19.setCellValue(phone);
                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(phone);
                    Cell cell21 = row.createCell(21);
                    cell21.setCellValue(phone);*/
                    
                    
                    
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效  
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook 
     * @param 
     * @param 
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException, InvalidFormatException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(OPCPackage.open(in));
        }
        return wb;
    }
}
