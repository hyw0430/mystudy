package com.atguigu.formtag.action;

public class MarryAction {
	
	private boolean married = true;
	
	public void setMarried(boolean married) {
		this.married = married;
	}
	
	public boolean isMarried() {
		return married;
	}
	
	public String execute() {
		
		System.out.println("married="+married);
		
		return "success";
	}

}
