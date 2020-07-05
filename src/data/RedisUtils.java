package data;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	
	
	/**
	 * @param host, IP address
	 * @param port, REDIS port
	 * @return JedisPool, JedisConnection pool
	 * */
	public static JedisPool getJedisResource(String host, int port) {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		int timeout = 1 * 3000; // 1 Second
		jedisPoolConfig.setMaxWaitMillis(timeout);
		jedisPoolConfig.setMaxTotal(100);
		jedisPoolConfig.setTestOnBorrow(false);

		return new JedisPool(jedisPoolConfig, host, port, timeout);
	}
}
