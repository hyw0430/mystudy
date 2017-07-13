package com.atguigu.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//可以通过标记 @Order 注解来确定切面的优先级. @Order 的 value 值越小, 则其优先级越高
@Order(100)
@Aspect
@Component
public class LoggingAspect {
	/*
	{
		final Object target = null;
		
		InvocationHandler invocationHandler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object result = null;
				
				try {
					//前置通知. 一定会被执行. 
					//执行目标方法
					result = method.invoke(target, args);
					//返回通知. 方法正常结束, 可以访问到方法的返回值. 不一定被执行到, 当方法出现异常时, 返回通知就不会被执行.
				} catch (Exception e) {
					//异常通知. 方法出现异常时, 可以访问到异常对象. 不一定被执行到, 当方法出现异常时, 才会执行异常通知。
					e.printStackTrace();
				} finally{
					//后置通知. 一定会被执行. 不一定能够访问到返回值. 
				}
				
				return null;
			}
		};
	}
	*/

	//即为包装之后的动态代理调用. 
	@Around(value="delarePointcut()")
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
	
	//需要在一个方法上来声明切入点的表达式. 所以这个方法中尽量不要再编写其他的代码. 
	//使用 @Pointcut 注解来标记切点表达式. 下面通知可以使用 value 属性来引用该方法名. 
	@Pointcut("execution(public int com.atguigu.spring.aop.ArithmeticCalculator.*(int, int) )")
	public void delarePointcut(){}

	//异常通知. 使用 @AfterThrowing 的 throwing 属性来访问异常通知中的异常. 
	@AfterThrowing(throwing="ex", value="delarePointcut()")
	public void afterThrowing(JoinPoint joinPoint, Exception ex){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " occurs exception: " + ex.getMessage());
	}
	
	//返回通知. 可以访问到方法的返回值
	//访问返回值的方式: 在 @AfterReturning 注解的 returning 属性来标记返回值的名字. 同时在方法中传入对应名字的参数.
	@AfterReturning(returning="result", value="delarePointcut()")
	public void afterReturning(JoinPoint joinPoint, Object result){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " ends with " + result);
	}
	
	//前置通知
	@After(value="delarePointcut()")
	public void after(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " ends.");
	}
	
	//前置通知
	@Before(value="delarePointcut()")
	public void before(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}
}
