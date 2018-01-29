package com.wanghao.test.wanghao;

/**
 * Created by Administrator on 2018/1/26.
 */
public @interface MultiParamsAnnotation {
    //在使用的时候 可写可不写
    String paramString() default "";
    
    int paramInt() default 2;
    
    long paramLong();
    
    boolean paramBoolean();
    
    
}
