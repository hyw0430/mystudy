package com.atguigu.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//可以通过标记 @Order 注解来确定切面的优先级. @Order 的 value 值越小, 则其优先级越高
public class LoggingAspect {

	//即为包装之后的动态代理调用. 
	public Object around(ProceedingJoinPoint joinPoint){
		try {
			System.out.println("前置通知. ");
			//执行目标方法
			Object result = joinPoint.proceed();
			System.out.println("返回通知. ");
			return result;
		} catch (Throwable e) {
			//e.printStackTrace();
			System.out.println("异常通知. ");
		} finally{
			System.out.println("后置通知. ");
		}
		
		return null;
	}
	
	//异常通知. 使用 @AfterThrowing 的 throwing 属性来访问异常通知中的异常. 
	public void afterThrowing(JoinPoint joinPoint, Exception ex){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " occurs exception: " + ex.getMessage());
	}
	
	//返回通知. 可以访问到方法的返回值
	//访问返回值的方式: 在 @AfterReturning 注解的 returning 属性来标记返回值的名字. 同时在方法中传入对应名字的参数.
	public void afterReturning(JoinPoint joinPoint, Object result){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " ends with " + result);
	}
	
	//前置通知
	public void after(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " ends.");
	}
	
	//前置通知
	public void before(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}
}
