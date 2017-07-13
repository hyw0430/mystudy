package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

public class Test03 
{
	public static void main(String[] args) throws InterruptedException 
	{
		Jedis jedisM = new Jedis("127.0.0.1",6379);
		Jedis jedisS = new Jedis("127.0.0.1",6380);
		System.out.println(jedisM.get("k1"));
		
		jedisS.slaveof("127.0.0.1",6379);
		
		jedisM.set("k13","v13");
		Thread.sleep(1000);
		String result = jedisS.get("k13");
		System.out.println("********"+result);
	}
}
