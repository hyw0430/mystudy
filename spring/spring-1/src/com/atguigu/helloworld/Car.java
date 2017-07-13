package com.atguigu.helloworld;

public class Car {

	private String brand = "byd";
	private double price;
	private int speed;

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String brand, double price, int speed) {
		this.brand = brand;
		this.price = price;
		this.speed = speed;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", speed=" + speed
				+ "]";
	}
}
