package com.zhl.job.interceptor.anno;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *  
 *  不需要认证的方法注解
  * @ClassName: NoAuth
  * @author zhaotisheng	
  * @date 2017年4月11日 下午3:08:00
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuthAll {

	String value() default "ALL";
}
