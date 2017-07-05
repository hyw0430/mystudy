package com.atguigu.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	/**
	 * IOC 容器在调用 init-method 之后会调用该方法. 若没有 init-method 
	 * 则在 postProcessBeforeInitialization 之后就调用该方法.
	 * 
	 * @param arg0: bean 实例
	 * @param arg1: 在 IOC 容器中配置的 bean 的 id. 
	 * 
	 * @return: 返回值将直接给到 getBean 方法作为返回值
	 * 
	 * 作用: 在 init 前后对 bean 的属性进行调整. 通常在使用 BeanPostProcessor 时都需要使用 instanceof 对 bean 进行
	 * 先行的判断. 
	 */
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("[2222222]. arg0: " + arg0 + ", arg1: " + arg1);
		if(arg0 instanceof Company){
			System.out.println("postProcessAfterInitialization");
		}
		return arg0;
	}

	/**
	 * IOC 容器在调用 init-method 之前会调用该方法. 若没有 init-method 则在初始化属性之后就调用该方法.
	 * 
	 * @param arg0: bean 实例
	 * @param arg1: 在 IOC 容器中配置的 bean 的 id. 
	 */
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("[1111111]. arg0: " + arg0 + ", arg1: " + arg1);
		if(arg0 instanceof Company){
			System.out.println("postProcessBeforeInitialization");
		}
		return arg0;
	}

}
