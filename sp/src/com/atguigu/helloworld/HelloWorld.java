package com.atguigu.helloworld;



public class HelloWorld {

	private String name;
	
	public HelloWorld() {
		System.out.println("HelloWorld's Constructor...");
	}
	
	public void setName(String name) {
		System.out.println("setName:" + name);
		this.name = name;
	}
	
	public void sayHello(){
		System.out.println("Hello: " + name);
	}
	
}
