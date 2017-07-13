package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test04 
{
	public static void main(String[] args) 
	{
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			jedis.set("k33","v33");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JedisPoolUtil.release(jedisPool, jedis);
		}
	}
}
