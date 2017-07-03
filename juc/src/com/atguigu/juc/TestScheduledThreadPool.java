package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * һ���̳߳أ��ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴��������ٶ��⿪�����������Ӧ���ٶȡ�
 * 
 * �����̳߳ص���ϵ�ṹ��
 * 	java.util.concurrent.Executor : �����̵߳�ʹ������ȵĸ��ӿ�
 * 		|--**ExecutorService �ӽӿ�: �̳߳ص���Ҫ�ӿ�
 * 			|--ThreadPoolExecutor �̳߳ص�ʵ����
 * 			|--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 * 				|--ScheduledThreadPoolExecutor ���̳� ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 * 
 * ���������� : Executors 
 * ExecutorService newFixedThreadPool() : �����̶���С���̳߳�
 * ExecutorService newCachedThreadPool() : �����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 * ExecutorService newSingleThreadExecutor() : ���������̳߳ء��̳߳���ֻ��һ���߳�
 * 
 * ScheduledExecutorService newScheduledThreadPool() : �����̶���С���̣߳������ӳٻ�ʱ��ִ������
 */
public class TestScheduledThreadPool {

	public static void main(String[] args) throws Exception {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

		for (int i = 0; i < 15; i++) {
			Future<Integer> result = pool.schedule(new Callable<Integer>(){

				@Override
				public Integer call() throws Exception {
					int num = new Random().nextInt(100);//���������
					System.out.println(Thread.currentThread().getName() + " : " + num);
					return num;
				}
				
			}, 1, TimeUnit.SECONDS);
			
			System.out.println(result.get());
		}
		
		pool.shutdown();
	}
	
}
