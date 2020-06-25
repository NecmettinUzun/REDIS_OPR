package main;

import java.util.Optional;
import java.util.Scanner;

public class Starter {

	public static void main(String[] args) {

		System.out.println("Redis Type:");
		printRedisTypeInfo();

		try (Scanner in = new Scanner(System.in);) {

			Optional<RedisType> redisType = RedisType.getRedisType(in.nextInt());

			if (redisType.isEmpty()) {
				System.out.println("Redis Type not found!!");
				return;
			}

			switch (redisType.get()) {
			case IMSI_IMEI:
				break;
			case IMSI_MSISDN:
				break;
			case MSISDN_IMSI:
				break;
			default:
				System.out.println("Redis type not found!!" + redisType.get().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printRedisTypeInfo() {

		for (RedisType redisType : RedisType.values()) {
			System.out.println(redisType.getName() + " : " + redisType.getId());
		}

	}

}
