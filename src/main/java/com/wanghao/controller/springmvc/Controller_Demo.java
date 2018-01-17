package com.wanghao.controller.springmvc;

import com.wanghao.controller.StudentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghao on 2016/12/31.
 */

@Controller
@RequestMapping("/demo")
public class Controller_Demo {
	private Logger logger = LoggerFactory.getLogger(Controller_Demo.class);


	/**
	 * 通过调用 http://localhost:8084/demo/hello.do 返回到hello.jsp的页面,
	 *<br> 这个在spring-mvc.xml 里面的视图解析器里面配置的.
	 * @return
	 */
	@RequestMapping("/hello.do")
	public String hello(){
		return "hello";
	}

	/***<b>自动注入</b>
	 * 在springmvc中 存在单个参数自动注入的情况,
	 * @param age
	 * @param name
	 * @return
	 */
	@RequestMapping("/autoSet.do")
	public String autoSet(int age,String name){
		logger.info("age:"+age+" name:"+name);
		return "hello";
	}

	/***<b>自动装箱</b>
	 * 在springmvc中 存在自动装箱的情况,
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/autoBox.do")
	public String autoBox(StudentInfo studentInfo){
		logger.info("自动装箱后的结果:"+studentInfo);
		return "hello";
	}

	
/*	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class,new CustomBooleanEditor(new SimpleDateFormat("yyyy-mm-dd"),true));
	}
	*/


    /**
     * 能够往前台传递参数${requestScope.p}  在前台可以这样获取到
     * @param map
     * @return
     */
    @RequestMapping("/toFront.do")
    public String toFront(Map<String,String> map){
        logger.info("自动装箱后的结果:"+map);
        map.put("p","123");
        map.put("a","a");
        return "hello";
    }

    /**
     * 这是一个 转发, 相当于把上面的redirect.do 变成了  hello.do
     * @return
     */
    @RequestMapping("/redirect.do")
    public String redirect(){
        return "redirect:hello.do";
    }


    /***
     * RequestParam
     * 可以让后面的参数和变量名不一样.
     */
    @RequestMapping("/param.do")
    public String  RequestParam(@RequestParam(value = "id") int idxxx,@RequestParam(value = "name") String name2){
        logger.info("id:"+idxxx+"   name:"+name2);
        return "hello";
    }


    /***
     * 使用restful风格的springmvc
     * 可以让后面的参数和变量名不一样.
     */
    @RequestMapping(value = "/get/{id}/a.do",method = RequestMethod.GET)
    public String  get(@PathVariable("id") int id){
        logger.info("restful id:"+id);
        return "hello";
    }



    /***
     * 使用restful风格的springmvc
     * 可以让后面的参数和变量名不一样.
     */
    @RequestMapping(value = "/json.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String>  returnmap(){
        logger.info("返回json格式:");
        Map map=new HashMap<String, String>();
        map.put("code","123");
        return map;
    }


}
