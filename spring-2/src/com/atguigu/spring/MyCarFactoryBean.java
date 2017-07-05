package com.atguigu.spring;

import org.springframework.beans.factory.FactoryBean;

//在 IOC 容器中配置后, 返回的不是当前类的实例, 而是 getObject 返回值所对应的实例. 
//若有一些 bean 若直接在 ioc 容器中配置不方便, 则使用 FactoryBean 可以简化其配置. 多见于 Spring 整合第三方框架. 
//自定以使用场景很少. 
public class MyCarFactoryBean implements FactoryBean<Car>{

	//返回的 bean 的实例
	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setBrand("benz");
		car.setPrice(250000);
		
		return car;
	}

	//返回的 bean 的类型
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}
	
	//返回的 bean 是否为单例的
	@Override
	public boolean isSingleton() {
		return true;
	}

}
