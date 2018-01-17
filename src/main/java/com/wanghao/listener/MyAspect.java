package com.wanghao.listener;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年3月2日 下午4:03:55 
* 类说明 
*/
public class MyAspect {
    
    public void afterMethod(JoinPoint joinPoint,Object retValue) {
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
       System.out.println("---------类名"+targetName);
       System.out.println("---------方法名"+methodName);
       System.out.println("return is "+retValue);
    }  
    
    public void beforeMethod(JoinPoint joinPoint) throws Exception{
        
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest req = sra.getRequest();
       
       String requestPath = req.getRequestURI();
      
       
       req.setCharacterEncoding("UTF8");
       
       //获取请求的所有报文头信息并打印
       Enumeration<String> enumHeader = req.getHeaderNames();
       StringBuffer sbHeader = new StringBuffer();
       sbHeader.append("\n");
       sbHeader.append("##################################################");
       sbHeader.append("\n");
       sbHeader.append("##########销管进件接收请求,url={"+req.getServletPath()+"},url全路径:{");
       sbHeader.append(req.getRequestURL()).append("}");
       sbHeader.append("\n");
       sbHeader.append("##########销管进件接收请求,报文头信息={");
       sbHeader.append("\n");
       while(enumHeader.hasMoreElements()){
           String key = enumHeader.nextElement();
           sbHeader.append("["+key+"] = {"+req.getHeader(key)+"}, ");
           sbHeader.append("\n");
       }
       sbHeader.append("}");
       sbHeader.append("\n");
       
       //获取请求的所有参数信息并打印
       Enumeration<String> enumParam = req.getParameterNames();
       StringBuffer sbParam = new StringBuffer();
       sbParam.append("##########销管进件接收请求,参数信息={");
       sbParam.append("\n");
       while(enumParam.hasMoreElements()){
           String key = enumParam.nextElement();
           sbParam.append("["+key+"] = {"+req.getParameter(key).replace("\r|\n|\t", "")+"}, ");
           sbParam.append("\n");
       }
       sbParam.append("}");
       sbParam.append("\n");
       
       
       System.out.println("请求路径:"+requestPath);
       System.out.println("请求头:"+sbHeader);
       System.out.println("请求参数:"+sbParam);
        
    }
}
 