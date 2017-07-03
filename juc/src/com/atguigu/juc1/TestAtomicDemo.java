package com.atguigu.juc1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nicky һ ��i++��ԭ���� temp=i i=i+1 i=temp ����ԭ�ӱ��� jdk1.5�Ժ�
 *         java.util.concurrent.atomic�����ṩ�˳��õ�ԭ���� 1.volatitle ��֤�ڴ�ɼ��� 2.cas
 *         �㷨��֤���ݵ�ԭ���� cas�㷨��Ӳ���Բ��������������ݵ�֧�� CAS ������������������ ���ڴ�ֵ V ��Ԥ��ֵ A �۸���ֵ B
 *         ���ҽ��� V == A ʱ�� V = B; ���򣬲���ִ���κβ�����
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