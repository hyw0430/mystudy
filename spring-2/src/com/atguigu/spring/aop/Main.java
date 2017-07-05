package com.atguigu.spring.aop;

public class Main {
	
	public static void main(String[] args) {
		ArithmeticCalculator arithmeticCalculator = 
				new ArithmeticCalculatorImpl();
		
		arithmeticCalculator = new ArithmeticCalculatorLoggingProxy(arithmeticCalculator).getLoggingProxy();
		
		int result = arithmeticCalculator.add(1, 2);
		System.out.println(result);
		
		result = arithmeticCalculator.mul(11, 2);
		System.out.println(result);
	}
	
}
