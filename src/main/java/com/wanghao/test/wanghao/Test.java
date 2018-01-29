package com.wanghao.test.wanghao;/**
 * Created by Administrator on 2018/1/26.
 */

/**
 * @author WangH
 * @create 2018-01-26 14:05
 **/
public class Test {
    //没有写 defalut
    @MultiParamsAnnotation(paramInt = 1,paramLong = 2,paramBoolean = true)
    private String argOne="";
    
    @MultiParamsAnnotation(paramBoolean = false,paramLong = 2,paramInt = 3,paramString = "123")
    private String argTwo="";
    
}
