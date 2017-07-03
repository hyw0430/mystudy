package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * �����������߰�����
 */
public class TestProductorAndConsumerForLock {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		Productor pro = new Productor(clerk);
		Consumer con = new Consumer(clerk);

		new Thread(pro, "������ A").start();
		new Thread(con, "������ B").start();

//		 new Thread(pro, "������ C").start();
//		 new Thread(con, "������ D").start();
	}

}

class Clerk {
	private int product = 0;

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	// ����
	public void get() {
		lock.lock();

		try {
			if (product >= 1) { // Ϊ�˱�����ٻ��ѣ�Ӧ������ʹ����ѭ���С�
				System.out.println("��Ʒ������");

				try {
					condition.await();
				} catch (InterruptedException e) {
				}

			}
			System.out.println(Thread.currentThread().getName() + " : "
					+ ++product);

			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	// ����
	public void sale() {
		lock.lock();

		try {
			if (product <= 0) {
				System.out.println("ȱ����");

				try {
					condition.await();
				} catch (InterruptedException e) {
				}
			}

			System.out.println(Thread.currentThread().getName() + " : "
					+ --product);

			condition.signalAll();

		} finally {
			lock.unlock();
		}
	}
}

// ������
class Productor implements Runnable {

	private Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			clerk.get();
		}
	}
}

// ������
class Consumer implements Runnable {

	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}

}