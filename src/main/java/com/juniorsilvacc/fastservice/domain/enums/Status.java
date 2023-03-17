package com.juniorsilvacc.fastservice.domain.enums;

public enum Status {
	
	PENDING(1),
	SENT(2),
	FINISHED(3);
	
	private int code;

	private Status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Status valueOf(int code) {
		for (Status value : Status.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid status code");
	}
	
	

}
