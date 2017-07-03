package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		AlternateDemo ad = new AlternateDemo();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 20; i++) {
					ad.loopA(i);
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 20; i++) {
					ad.loopB(i);
				}
			}
		}, "B").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 20; i++) {
					ad.loopC(i);
					System.out.println("-------------------------------------");
				}
			}
		}, "C").start();
	}

}

class AlternateDemo {
	private int number = 1;// 当前线程标记
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void loopA(int totalLoop) {
		lock.lock();

		try {

			if (number != 1) {
				condition1.await();
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 2;
			condition2.signal();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			lock.unlock();
		}
	}

	public void loopB(int totalLoop) {
		lock.lock();

		try {

			if (number != 2) {
				condition2.await();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 3;
			condition3.signal();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			lock.unlock();
		}
	}

	public void loopC(int totalLoop) {
		lock.lock();

		try {

			if (number != 3) {
				condition3.await();
			}
			for (int i = 1; i <= 20; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 1;
			condition1.signal();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			lock.unlock();
		}
	}

}
