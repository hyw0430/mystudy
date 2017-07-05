package com.atguigu.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// HelloWorld helloWorld = new HelloWorld();
		// helloWorld.setName("nicky");
		// helloWorld.say();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld bean = (HelloWorld) ctx.getBean("hwd");
		bean.sayHello();
	}
}
