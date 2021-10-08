package com.bridgelabz.api.exception;

import java.util.Locale;

import com.bridgelabz.api.util.ResponseDTO;

import lombok.Data;

@Data
public class UserRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int StatusCode;
	private String Statusmessage;
	
	public UserRegisteredException(int statusCode, String statusmessage) {
		super(statusmessage);
		StatusCode = statusCode;
		Statusmessage = statusmessage;
	}
}
