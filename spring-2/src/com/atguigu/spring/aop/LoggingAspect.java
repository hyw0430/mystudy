package com.atguigu.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	@Before("execution(public int com.atguigu.spring.aop.ArithmeticCalculator.add(int, int))")
	public void beforeLogging(JoinPoint joinPoint){
		System.out.println("The method " 
					+ joinPoint.getSignature().getName() 
					+ " begins with " 
					+ Arrays.asList(joinPoint.getArgs()));
	}
	
}
