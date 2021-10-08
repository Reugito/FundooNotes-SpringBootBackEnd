package com.bridgelabz.api.util;

import lombok.Data;

public @Data class ResponseDTO {
	
	int status;
	String message;
	Object object;
	
	public ResponseDTO(int status, String message, Object object) {

		this.status = status;
		this.message = message;
		this.object = object;
	}
	
	
	

}
