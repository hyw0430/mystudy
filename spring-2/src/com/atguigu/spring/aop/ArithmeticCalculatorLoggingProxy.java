package com.atguigu.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {

	//被代理的对象.
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy(){
		ArithmeticCalculator proxy = null;
		
		//类加载器. 产生的代理对象由哪一个类加载器来加载. 
		ClassLoader loader = target.getClass().getClassLoader();
		//指定 Class 类型的数组. 代理产生的对象是什么类型的. 必须是接口类型. 
		Class [] interfaces = target.getClass().getInterfaces();
		//若调用代理对象方法, 方法将如何进行相应. 
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy: 返回的代理对象. 几乎不使用. 
			 * method: 正在被调用的方法. 
			 * args: 正在调用方法传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String methodName = method.getName();
				System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
				
				//执行目标方法
				Object result = method.invoke(target, args);
				
				System.out.println("The method " + methodName + " ends with " + result);
				
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
