package data;

import java.util.Optional;

public enum OperationType {

	GET(0,"GET"),
	SET(1,"SET");
	
	private int id;
	private String name;
	
	private OperationType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static OperationType getOperationType(int id){
		
		for (OperationType operation : OperationType.values()) {
			if(id == operation.getId()) {
				return operation;
			}
		}
		return null;
	}
}
