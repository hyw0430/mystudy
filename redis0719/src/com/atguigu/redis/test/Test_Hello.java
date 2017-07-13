package com.atguigu.redis.test;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test_Hello 
{
	public static void main(String[] args) 
	{
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.ping());
		
		//key
		Set<String> keys = jedis.keys("*");
		System.out.println(keys.size());
		Long ret = jedis.ttl("k1");
		System.out.println(ret);
		
		//五大数据类型作为家庭作业，自己联系，参考脑图。
	}
}
