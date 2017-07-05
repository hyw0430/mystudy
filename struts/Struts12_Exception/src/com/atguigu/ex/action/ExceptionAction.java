package com.atguigu.ex.action;

public class ExceptionAction {
	
	private int num;
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String daoMeiAction() {
		
		int result = 10 / num;
		
		System.out.println("result="+result);
		
		return "success";
	}
	
	public String daoDanAction() {
		
		int result = 10 / num;
		
		System.out.println("result="+result);
		
		return "success";
	}

}
