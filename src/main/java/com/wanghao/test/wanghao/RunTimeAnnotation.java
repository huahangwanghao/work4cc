package com.wanghao.test.wanghao;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2018/1/26.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)  //表示 运行时可见
public @interface RunTimeAnnotation {
}
