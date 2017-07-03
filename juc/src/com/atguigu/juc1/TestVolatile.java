package com.atguigu.juc1;

import java.lang.invoke.VolatileCallSite;

/**
 * volatitle �ؼ���:��֤����̲߳�����������ʱ���ɱ�֤�ڴ�������ǿɼ���
 * 			�����synchronized��һ�ֽ�Ϊ��������ͬ������
 * ע�⣺volatile ���߱�������
 * 		���ܱ�֤������ԭ����
 */
public class TestVolatile {
	public static void main(String[] args) {

		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		
		while(true){//Ч�ʸߡ���ǰ������flag=false 
			if(td.isFlag()){
			System.out.println("---------");
			break;}
		}
		
	}
}

class ThreadDemo implements Runnable {

	private volatile  boolean flag = false;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
		flag = true;
		System.out.println("flag=" + isFlag());
	}

}