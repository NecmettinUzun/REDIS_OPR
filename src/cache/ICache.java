package cache;

import data.RedisConnectionInfo;

public interface ICache {

	public void set(String key, String value);

	public String get(String key);
	
	public void initialize(RedisConnectionInfo redisConnInfo);
}
