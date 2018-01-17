package com.wanghao.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanghao.listener.SystemControllerLog;

/**
 * Created by wanghao on 2016/12/31.
 */

@Controller
public class Product {
	private Logger logger = LoggerFactory.getLogger(Product.class);

	/*@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(){
	    
		for(int i=0;i<0;i++){
			CommissionVO c=new CommissionVO();
			logger.info("to send message:{}",c);
			amqpTemplate.convertAndSend("commissionQueue1","{\"addOrderDate\":1487759262000,\"appIdNo\":\"MF201702161400008065\",\"productAmount\":\"0.00\",\"productId\":\"M000000000100000024\"}");
		}

	}*/
	
public void sendMessage(){
    
}
        
        

	@RequestMapping(value = {"test"}, method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog
	public String test(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	    System.out.println("this is contreller");
	    resp.getWriter().write("this is response");
	    return "wanghao";
	    
	}
	@RequestMapping(value = {"test1"}, method = RequestMethod.GET)
	 @ResponseBody
	public String test1(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	    //resp.reset();
        System.out.println("this is test1");
       // resp.getWriter().write("this is test1");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username", "123");
        map.put("pawd", "123123");
        return map.toString();
        
    }
}
