package cache;

import data.RedisConnectionInfo;
import data.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ImsiImeiRedisCache implements ICache {

	private JedisPool jedisPool;
	private Jedis jedis;
	
	@Override
	public void set(String key, String value) {
		
		try {
			jedis.getSet(key, value);
			System.out.println("Insert successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public String get(String key) {
		String result = null;
		try {
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void initialize(RedisConnectionInfo redisConnInfo) {
		jedisPool = RedisUtils.getJedisResource(redisConnInfo.getIp(), redisConnInfo.getPort());

		try {
			jedis = jedisPool.getResource();
			if (jedis.isConnected()) {
				System.out.println("Connected Redis:)");
				
					if (redisConnInfo.isMaster()) {
						set(redisConnInfo.getKey(), redisConnInfo.getValue());
					} else {
						String value = get(redisConnInfo.getKey());
						System.out.println("KEY: " + redisConnInfo.getKey() + " VALUE: " + value);
					}
				
			} else {
				System.out.println("Not Connected Redis:(");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

	}

}
