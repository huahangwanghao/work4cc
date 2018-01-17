package com.wanghao.controller;

import com.wanghao.utils.PropertyUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wanghao on 2016/12/31.
 */

@Controller
@RequestMapping("/sys")
public class WorkController {
	private Logger logger = LoggerFactory.getLogger(WorkController.class);


	@RequestMapping(value = "login.do")
	@ResponseBody
	public Object login(@RequestBody SysUser req, HttpServletRequest request) {
		Map<String, Object> map = this.getMap("success");
		
		String userName=req.getLoginid();
		String pwd=req.getPwd();
		String pwd1=PropertyUtil.getPwd(userName);
		
		
		boolean loginsuccess = false;
		logger.info("登录入参:"+req);
		if(null!=pwd1){
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(60*5);
			session.setAttribute(req.getLoginid(),req.getLoginid());
			loginsuccess=true;
		}
		
		if (!loginsuccess) {
			map.put("code", 1);
			map.put("restr", "fail");
		}
		return map;
	}
	
	

	public static Map<String, Object> getMap(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("restr", msg);
		return map;
	}
	
	public String write2Disk(File filename) throws Exception {
		    /* 读入TXT文件 */
		    
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(filename),"utf-8"); // 建立一个输入流对象reader  
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
		String filePath="D:\\2.txt";
		String osName=System.getProperty("os.name");
		if(!osName.toUpperCase().contains("WIN")){
			filePath="/tmp/2.txt";
		}
		File writename = new File(filePath); // 相对路径，如果没有则要建立一个新的output。txt文件  
		writename.createNewFile(); // 创建新文件  
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		out.write(newLine); // \r\n即为换行  
		out.flush(); // 把缓存区内容压入文件  
		out.close(); // 最后记得关闭文件  
		return filePath;
	}
	
	@RequestMapping(value = {"fileUpload2.do"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  fileUpload2(HttpServletRequest request,@RequestParam("userName")String userName,@RequestParam("file") CommonsMultipartFile file) throws Exception {
		
		logger.info("上传文件入参:"+userName);
		String osName=System.getProperty("os.name");
		osName=osName.toUpperCase();
		
		if(userName==null){
			return getMap("请先登录!");
		}
		if(request.getSession().getAttribute(userName)==null){
			return getMap("session 过期,请重新登录!");
		}
		
		if(file==null){
			return getMap("请选择文件!");
		}
		if(file.getOriginalFilename().length()==0){
			return getMap("请选择文件!");
		}


		long  startTime=System.currentTimeMillis();
		System.out.println("fileName："+file.getOriginalFilename());
		String path="D:/"+new Date().getTime()+file.getOriginalFilename();
		String writePath="D://admin//1.xlsx";
		
		if(osName.contains("WIN")){
			//path="D:/"+new Date().getTime()+file.getOriginalFilename();
		}else{
			path="/tmp/"+new Date().getTime()+file.getOriginalFilename();
			writePath="/tmp/"+userName+"/1.xlsx";
		}
		logger.info("编写的位置:"+writePath);
		createFile(writePath);
		File newFile=new File(path);
		//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		path=write2Disk(newFile);
		long  endTime=System.currentTimeMillis();
		System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");



		File filename2 = new File(path); // 要读取以上路径的input。txt文件  
		InputStreamReader reader2 = new InputStreamReader(
				new FileInputStream(filename2)); // 建立一个输入流对象reader  
		BufferedReader br2 = new BufferedReader(reader2); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
		String line2 = "";
		line2 = br2.readLine();
		//System.out.println(line2);
		List<StudentInfo> list=new ArrayList<StudentInfo>();
		while (line2 != null) {
			line2 = br2.readLine();
			if(line2!=null&&line2.contains("客户姓名 ")){
				continue;
			}
			//System.out.println(line2);
			if(line2!=null)
				list.add(getStudetInfo(line2));
		}
		
		
		WriteExcel.writeExcel(list,20,writePath);
		



		return getMap("success");
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


	/**
	 * 文件下载
	 * @Description:
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/download.do")
	public String downloadFile(@RequestParam("fileName") String fileName,
							   HttpServletRequest request, HttpServletResponse response,String userName) {
		if(userName==null){
			return "hello";
		}
		if(request.getSession().getAttribute(userName)==null){
			return "hello";
		}
		if (fileName != null) {
			String realPath = request.getServletContext().getRealPath(
					"WEB-INF/File/");
			String osName=System.getProperty("os.name");
			osName=osName.toUpperCase();
			realPath="D://";
			if(!osName.contains("WIN")){
				realPath="/tmp/"+userName+"/";
			}
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition",
						"attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}

	
	
	private  void createFile(String path){ 
		File file = new File(path);
		if(!file.exists()){
			try {
				File f=file.getParentFile();
				f.mkdir();
				file.createNewFile();
				try {
					testCreateFirstExcel07(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void testCreateFirstExcel07(String path) throws Exception {
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
		fileOut.close();
	}

}
