package cache;

import com.google.common.primitives.Longs;

import data.RedisConnectionInfo;
import data.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MsisdnImsiRedisCache implements ICache {

	private JedisPool jedisPool;
	private Jedis jedis;
	
	@Override
	public void set(String key, String value) {
		try {
			long longKey = Long.parseLong(key);
			long longValue = Long.parseLong(value);
			
			jedis.getSet(Longs.toByteArray(longKey), Longs.toByteArray(longValue));
			System.out.println("Insert successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public String get(String key) {
		try {
			byte[] value = jedis.get(Longs.toByteArray(Long.parseLong(key)));
			long result = Longs.fromByteArray(value);
			return String.valueOf(result);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
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
