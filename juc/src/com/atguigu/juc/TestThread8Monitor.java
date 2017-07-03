package com.atguigu.juc;

/*
 * ��Ŀ���жϴ�ӡ�� "one" or "two" ��
 * 
 * 1. ������ͨͬ�������������̣߳���׼��ӡ�� ��ӡ? //one  two
 * 2. ���� Thread.sleep() �� getOne() ,��ӡ? //one  two
 * 3. ������ͨ���� getThree() , ��ӡ? //three  one   two
 * 4. ������ͨͬ������������ Number ���󣬴�ӡ?  //two  one
 * 5. �޸� getOne() Ϊ��̬ͬ����������ӡ?  //two   one
 * 6. �޸�����������Ϊ��̬ͬ��������һ�� Number ����?  //one   two
 * 7. һ����̬ͬ��������һ���Ǿ�̬ͬ������������ Number ����?  //two  one
 * 8. ������̬ͬ������������ Number ����?   //one  two
 * 
 * �̰߳����Ĺؼ���
 * �ٷǾ�̬��������Ĭ��Ϊ  this,  ��̬��������Ϊ ��Ӧ�� Class ʵ��
 * ��ĳһ��ʱ���ڣ�ֻ����һ���̳߳����������ۼ���������
 */
public class TestThread8Monitor {
	
	public static void main(String[] args) {
		Number number = new Number();
		Number number2 = new Number();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getOne();
			} 
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getTwo();
//				number2.getTwo();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getThree();
			}
		}).start();
		
	}

}

class Number{
	
	public static synchronized void getOne(){//Number.class
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("one");
	}
	
	public synchronized void getTwo(){//this
		System.out.println("two");
	}
	
	public void getThree(){
		System.out.println("three");
	}
	
}