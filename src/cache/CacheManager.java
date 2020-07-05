package cache;

import java.util.Objects;

public class CacheManager {

	private static CacheManager instance = null;
	static Object object = new Object();
	ICache redisCache = null;

	public static CacheManager getInstance() {
		synchronized (object) {
			if (Objects.isNull(instance)) {
				instance = new CacheManager();
				return instance;
			}
			return instance;
		}
	}

	/**
	 * @param redisCache, redisCache which is already implemented IRedisCache
	 *                    interface
	 * @return void, assign new Object to redisCache variable
	 */
	public void setRedisCache(ICache redisCache) {
		this.redisCache = redisCache;
	}

	/**
	 * @return redisCache
	 * */
	public ICache getRedisCache() {
		return this.redisCache;
	}
	/**
	 * @param key,   key part in the key/value pair
	 * @param value, value part in the key/value pair
	 * @return void, inserts the specified key/value pair to the REDIS
	 */
	public void insertDataToRedis(String key, String value) {
		if (Objects.nonNull(redisCache)) {
			redisCache.set(key, value);
		} else {
			System.out.println("RedisCache not initialized!!");
		}
	}

	/**
	 * @param key, key which is used to search on the REDIS
	 * @return String, the value which is found from the REDIS
	 */
	private String extractDataFromRedis(String key) {

		if (Objects.nonNull(redisCache)) {
			return redisCache.get(key);
		} else {
			System.out.println("RedisCache not initialized!!");
			return null;
		}
	}
}
