package com.atguigu.juc1;

import java.lang.invoke.VolatileCallSite;

/**
 * volatitle 关键字:保证多个线程操作共享数据时，可保证内存的数据是可见的
 * 			相较于synchronized是一种较为轻量级的同步策略
 * 注意：volatile 不具备呼哧性
 * 		不能保证变量的原子性
 */
public class TestVolatile {
	public static void main(String[] args) {

		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		
		while(true){//效率高。提前读到了flag=false 
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