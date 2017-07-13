package com.atguigu.helloworld;

public class CarAction {
	
	private CarService carService;
	
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	
	public String execute(){
		System.out.println("[CarAction]#execute");
		carService.save();
		return null;
	}
	
}
