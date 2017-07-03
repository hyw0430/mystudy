package com.atguigu.juc1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nicky 一 、i++的原子性 temp=i i=i+1 i=temp 二、原子变量 jdk1.5以后
 *         java.util.concurrent.atomic报下提供了常用的原子性 1.volatitle 保证内存可见性 2.cas
 *         算法保证数据的原子性 cas算法是硬件对并发操作共享数据的支持 CAS 包含了三个操作数： ①内存值 V ②预估值 A ③更新值 B
 *         当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
 *
 */
public class TestAtomicDemo {
	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();

		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}
	}

}

class AtomicDemo implements Runnable {
	// private int serialNumber = 0 ;
	private AtomicInteger serialNumber = new AtomicInteger();

	public int getSerialNumber() {
		return serialNumber.getAndIncrement();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
	}

}