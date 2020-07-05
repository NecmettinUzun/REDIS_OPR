package data;

import java.util.Optional;

public enum RedisType {


	IMSI_IMEI(0,"IMSI_IMEI"),
	IMSI_MSISDN(1,"IMSI_MSISDN"),
	MSISDN_IMSI(2,"MSISDN_IMSI");
	
	private int id;
	private String name;

	 RedisType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	 
	 public int getId() {
		 return this.id;
	 }
	 
	 public String getName() {
		 return this.name;
	 }
	 
	 public static RedisType getRedisType(int id) {
		 for (RedisType redisType : RedisType.values()) {
			if(id == redisType.getId()) {
				return redisType;
			}
		}
		 return null;
	 }
}
