package com.atguigu.helloworld;

public class CarService {

	private CarDao carDao;
	
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}
	
	public void save() {
		System.out.println("[CarService]#save");
		carDao.save();
	}

}
