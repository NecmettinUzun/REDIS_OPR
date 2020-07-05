package data;

public class RedisConnectionInfo {

	private int port;
	private String Ip;
	private boolean isMaster;
	private String key;
	private String value;
	
	public RedisConnectionInfo(int port, String Ip, boolean isMaster,String key, String value) {
		this.port = port;
		this.Ip = Ip;
		this.isMaster = isMaster;
		this.key = key;
		this.value = value;
	}
	
	
	public String getValue() {
		return this.value;
	}
	
	public String getKey() {
		return this.key;
	}

	public int getPort() {
		return this.port;
	}
	
	public String getIp() {
		return this.Ip;
	}
	
	public boolean isMaster() {
		return this.isMaster;
	}
	
	@Override
	public String toString() {
		return "RedisConnectionInfo [port=" + port + ", Ip=" + Ip + ", isMaster=" + isMaster + "]";
	}
}
