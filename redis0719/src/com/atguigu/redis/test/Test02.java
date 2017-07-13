package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Test02 
{
	public static void main(String[] args) 
	{
		Jedis jedis = new Jedis("127.0.0.1",6379);
		
		jedis.watch("k1");
		jedis.set("k1","xxx");
		Transaction transaction = jedis.multi();//开启事务命令,批量执行操作。
		transaction.set("k1","111");
		transaction.set("k2","222");
		transaction.exec();
		//transaction.discard();取消事务
		System.out.println("------------ok");
	}
}
