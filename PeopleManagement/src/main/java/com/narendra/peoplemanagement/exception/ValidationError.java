package com.narendra.peoplemanagement.exception;

/**
 * To send the validation errors in the response
 * @author Narendra Korrapati
 *
 */
public class ValidationError {

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
