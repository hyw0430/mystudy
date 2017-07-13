package com.atguigu.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(50)
@Aspect
@Component
public class ValidationAspect {

	@Before(value="LoggingAspect.delarePointcut()")
	public void validate(){
		System.out.println("validate...");
	}
	
}
