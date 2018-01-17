package com.wanghao.controller.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * ControllerAdvice 这个让这个controler成为 全局的异常处理类
 * Created by wanghao on 2016/12/31.
 */

@ControllerAdvice
public class Controller_Exception {
	private Logger logger = LoggerFactory.getLogger(Controller_Exception.class);


	@ExceptionHandler
    @ResponseBody
    public String exception(Exception e){
        return "exception";
    }
}
