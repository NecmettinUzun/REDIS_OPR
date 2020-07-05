package main;

import data.OperationType;
import data.RedisType;

public class PrintConsole {

	/**
	 * @return void, PrintRedisType info to the Console
	 * */
	public static void printRedisTypeInfo(String warningMessage) {

		System.out.println(warningMessage);
		for (RedisType redisType : RedisType.values()) {
			System.out.println(redisType.getName() + " : " + redisType.getId());
		}

	}
	
	
	/**
	 * @return void, Print operation type to the Console
	 * */
	public static void printOperationType(String warningMessage) {
		
		System.out.println(warningMessage);
		for (OperationType redisOperationType : OperationType.values()) {
			System.out.println(redisOperationType.getName() + " : " + redisOperationType.getId());
		}
	}
}
