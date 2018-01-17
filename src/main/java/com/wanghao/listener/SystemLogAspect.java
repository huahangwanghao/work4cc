package com.wanghao.listener;



import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;  
 
/** 
* 切点类 
* @author tiangai 
* @since 2014-08-05 Pm 20:35 
* @version 1.0 
*/  
@Aspect  
@Component  
public  class SystemLogAspect {  
    
    
    private final ThreadLocal<Long> currentSeq = new ThreadLocal<Long>();
   //注入Service用于把日志保存数据库  
  
   //本地异常日志记录对象  
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);  
 
   //Service层切点  
   @Pointcut("@annotation(com.wanghao.listener.SystemServiceLog)")  
    public  void serviceAspect() {  
   }  
 
   //Controller层切点  
   @Pointcut("@annotation(com.wanghao.listener.SystemControllerLog)")  
    public  void controllerAspect() {  
   }  
   @Before("@within(com.wanghao.listener.SystemControllerLog)")  
   public void beforeType(JoinPoint joinPoint) {
       System.out.println("123123");
       before(joinPoint);
   } 
   @Before("@annotation(com.wanghao.listener.SystemControllerLog)")  
   public void beforeMethod(JoinPoint joinPoint) {
       System.out.println("this is 2");
       before(joinPoint);
   }  
  // @After("@annotation(com.wanghao.listener.SystemControllerLog)")  
   public void afterMethod(JoinPoint joinPoint,Object retValue) {
       HttpServletRequest  request = getHttpServletRequest(); 
       String targetName = joinPoint.getTarget().getClass().getName();  
       String methodName = joinPoint.getSignature().getName();  
       Object[] arguments = joinPoint.getArgs();  
       
       Class targetClass = null;
       try {
           targetClass = Class.forName(targetName);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }  
       Method[] methods = targetClass.getMethods();
       String operationName = "";
       for (Method method : methods) {  
           if (method.getName().equals(methodName)) {  
               Class[] clazzs = method.getParameterTypes();  
               if (clazzs!=null&&clazzs.length == arguments.length&&method.getAnnotation(SystemServiceLog.class)!=null) {  
                  // operationName = method.getAnnotation(SystemServiceLog.class).operationName();
                   break;  
               }  
           }  
       }
       long endTimeMillis = System.currentTimeMillis();
       //格式化开始时间
       String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
       //格式化结束时间
       String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);
       
       Object obj =request.getParameter("fileName");
       System.out.println("方法返回值: " + retValue);
       System.out.println(" 操作人: "+" 操作方法: "+operationName+" 操作开始时间: "+startTime +" 操作结束时间: "+endTime);
   }  
   
   /**
    * 这里不需要取得userAccessAnnotation
    * @param proceedingJoinPoint
    * @throws Throwable
    */
   @Around(value = "@within(com.wanghao.listener.SystemControllerLog) || " +
           "@annotation(com.wanghao.listener.SystemControllerLog)")
   public void beforeMethod1(ProceedingJoinPoint proceedingJoinPoint)  throws Throwable{
       System.out.println("this is 1");
       proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
       proceedingJoinPoint.getTarget().getClass();

   }
   
   /**
    * 之前打印日志
    *
    * @param joinPoint
    *            切点
    */
   private void before(JoinPoint joinPoint) {
      System.out.println("this is 3");
   }

   private boolean isSystemParam(Object object) {
       if (object == null) {
           return false;
       }
       return false;
   }
 
   /** 
    * 前置通知 用于拦截Controller层记录用户的操作 
    * 
    * @param joinPoint 切点 
    */  
   @Before("controllerAspect()")  
    public  void doBefore(JoinPoint joinPoint) {  
 
       //读取session中的用户  
  
       //请求的IP  
        try {  
           //*========控制台输出=========*//  
           System.out.println("this is 4");  
           System.out.println("请求方法: this is 5" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
           System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));  
           
           //*========数据库日志=========*//  
        
           System.out.println("this is 6=====前置通知结束=====");  
       }  catch (Exception e) {  
           //记录本地异常日志  
           logger.error("==前置通知异常==");  
           logger.error("异常信息:{}", e.getMessage());  
       }  
   }  
 
   /** 
    * 异常通知 用于拦截service层记录异常日志 
    * 
    * @param joinPoint 
    * @param e 
    */  
   @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")  
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
       //读取session中的用户  
      System.out.println("this is 7");
       //获取请求ip  
       //获取用户请求方法的参数并序列化为JSON格式字符串  
       String params = "";  
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
               params += (joinPoint.getArgs()[i]) + ";";  
           }  
       }  
        try {  
             /*========控制台输出=========*/  
           System.out.println("=====异常通知开始=====");  
           System.out.println("异常代码:" + e.getClass().getName());  
           System.out.println("异常信息:" + e.getMessage());  
           System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
           System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
           System.out.println("请求参数:" + params);  
              /*==========数据库日志=========*/  
           //保存数据库  
           System.out.println("=====异常通知结束=====");  
       }  catch (Exception ex) {  
           //记录本地异常日志  
           logger.error("==异常通知异常==");  
           logger.error("异常信息:{}", ex.getMessage());  
       }  
        /*==========记录本地异常日志==========*/  
 
   }  
 
 
   /** 
    * 获取注解中对方法的描述信息 用于service层注解 
    * 
    * @param joinPoint 切点 
    * @return 方法描述 
    * @throws Exception 
    */  
    public  static String getServiceMthodDescription(JoinPoint joinPoint)  
            throws Exception {  
       String targetName = joinPoint.getTarget().getClass().getName();  
       String methodName = joinPoint.getSignature().getName();  
       Object[] arguments = joinPoint.getArgs();  
       Class targetClass = Class.forName(targetName);  
       Method[] methods = targetClass.getMethods();  
       String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
               Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                   description = method.getAnnotation(SystemServiceLog. class).description();  
                    break;  
               }  
           }  
       }  
        return description;  
   }  
 
   /** 
    * 获取注解中对方法的描述信息 用于Controller层注解 
    * 
    * @param joinPoint 切点 
    * @return 方法描述 
    * @throws Exception 
    */  
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
       String targetName = joinPoint.getTarget().getClass().getName();  
       String methodName = joinPoint.getSignature().getName();  
       Object[] arguments = joinPoint.getArgs();  
       Class targetClass = Class.forName(targetName);  
       Method[] methods = targetClass.getMethods();  
       String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
               Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                   description = method.getAnnotation(SystemControllerLog. class).description();  
                    break;  
               }  
           }  
       }  
        return description;  
   }  
    /**
     * @Description: 获取request  
     * @author fei.lei  
     * @date 2016年11月23日 下午5:10 
     * @param  
     * @return HttpServletRequest
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}  
 