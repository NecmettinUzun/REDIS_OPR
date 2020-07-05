package main;

import java.util.Optional;
import java.util.Scanner;

import cache.CacheManager;
import cache.ICache;
import cache.ImsiImeiRedisCache;
import cache.ImsiMsisdnRedisCache;
import cache.MsisdnImsiRedisCache;
import data.OperationType;
import data.RedisConnectionInfo;
import data.RedisType;

public class Starter {

	public static void main(String[] args) {

		Starter starter = new Starter();
		PrintConsole.printRedisTypeInfo("Redis Type:");

		try (Scanner in = new Scanner(System.in);) {

			RedisType redisType = RedisType.getRedisType(in.nextInt());

			if (redisType == null) {
				System.out.println("Redis Type not found!!");
				return;
			}

			switch (redisType) {
			case IMSI_IMEI:
				starter.initializeCache(new ImsiImeiRedisCache());
				break;
			case IMSI_MSISDN:
				starter.initializeCache(new ImsiMsisdnRedisCache());
				break;
			case MSISDN_IMSI:
				starter.initializeCache(new MsisdnImsiRedisCache());
				break;
			default:
				System.out.println("Redis type not found!!" + redisType.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return void, initialize REDIS
	 */
	private void initializeCache(ICache redisCache) {

		CacheManager.getInstance().setRedisCache(redisCache);

		PrintConsole.printOperationType("Operation type:");

		RedisConnectionInfo redisConInfo = getRedisConnectionInfo();

		CacheManager.getInstance().getRedisCache().initialize(redisConInfo);
	}

	private RedisConnectionInfo settingRedisConnectionInfo(boolean isMaster) {

		String ip;
		int port;
		String key;
		String value = null;
		try (Scanner in = new Scanner(System.in);) {
			if (isMaster) {
				System.out.print("Master Redis IP:");
				ip = in.next();
				System.out.print("Master Redis Port:");
				port = Integer.parseInt(in.next());
				System.out.print("KEY:");
				key = in.next();
				System.out.print("VALUE:");
				value = in.next();
			} else {
				System.out.print("Slave Redis IP:");
				ip = in.next();
				System.out.print("Slave Redis Port:");
				port = Integer.parseInt(in.next());
				System.out.print("KEY:");
				key = in.next();
			}

			RedisConnectionInfo redisConInfo = new RedisConnectionInfo(port, ip, isMaster,key,value);
			return redisConInfo;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	private RedisConnectionInfo getRedisConnectionInfo() {
		RedisConnectionInfo redisConInfo = null;
		try (Scanner in = new Scanner(System.in);) {

			OperationType operationType = OperationType.getOperationType(in.nextInt());

			if (operationType == null) {
				System.out.println("Operation type not found !!");
			}

			switch (operationType) {
			case GET:
				redisConInfo = settingRedisConnectionInfo(false);
				break;
			case SET:
				redisConInfo = settingRedisConnectionInfo(true);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operationType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return redisConInfo;
	}

}
