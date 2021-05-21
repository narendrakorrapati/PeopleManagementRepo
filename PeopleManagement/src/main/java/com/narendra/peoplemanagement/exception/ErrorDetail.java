package com.narendra.peoplemanagement.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To send the custom error response when there is an Exception
 * @author Narendra Korrapati
 *
 */
public class ErrorDetail {

	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String message;
	private Map<String, List<ValidationError>> errors = new HashMap<String,
			List<ValidationError>>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}
	
}
