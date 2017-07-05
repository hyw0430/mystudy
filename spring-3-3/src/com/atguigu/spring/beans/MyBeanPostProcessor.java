package com.atguigu.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
	
	/**
	 * @param arg0: 当前的 bean 实例. 
	 * @param arg1: 当前的 bean 在 IOC 容器中配置的 id
	 * @return: 一般的直接返回 arg0. 返回值将作为调用 getBean 方法的返回值. 
	 * 
	 * 注意: 在方法内部通常需要使用 instanceof 来针对指定的 bean 进行处理。 
	 */
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("postProcessAfterInitialization:" + arg0 + ", " + arg1);
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization:" + arg0 + ", " + arg1);
		return arg0;
	}

}
