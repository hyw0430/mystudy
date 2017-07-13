package com.atguigu.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1. 加入 jar 包: 
 * 1). 日志包:
 * commons-logging-1.1.1.jar r
 * 2). Spring 必须的 jar 包
 * spring-beans-4.0.0.RELEASE.jar
 * spring-context-4.0.0.RELEASE.jar 
 * spring-core-4.0.0.RELEASE.jar
 * spring-expression-4.0.0.RELEASE.jar
 * 
 * 2. 利用 Spring 的插件创建 Spring 的配置文件
 * 
 * 3. 在配置文件中配置 bean:
 * <bean id="bean的id" class="bean的全类名">
 * 	<property name="属性名" value="属性值" />
 * </bean>
 * 
 * 4. 在 Main 方法中创建 IOC 容器. 
 * ApplicationContext ctx = new ClassPathXmlApplicationContext(spring配置文件的名字);
 * 
 * 5. 从 IOC 容器中取出 bean
 * HelloWorld helloWorld = (HelloWorld) ctx.getBean(bean的id);
 * 
 * 6. 使用 bean. 
 * 
 * 注意:
 * 1. bean 中必须有无参数的构造器
 * 2. 实际上, 在创建 IOC 容器时, 即创建了在配置文件中配置的 bean 的实例, 并为属性赋值了.
 * 
 */
public class Main {

	public static void main(String[] args) {
		// 1. 创建 IOC 容器
		// 在创建 IOC 容器时, 即创建了在配置文件中配置的 bean 的实例, 并为属性赋值了.
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2. 从 IOC 容器中获取 bean 的实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

		// 3. 调用 bean 的方法.
		helloWorld.sayHello();

		// HelloWorld helloWorld = new HelloWorld();
		// helloWorld.setName("atguigu");
		//
		// helloWorld.sayHello();
	}

}
