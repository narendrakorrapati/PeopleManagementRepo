package com.narendra.peoplemanagement.exception;

/**
 * This exception is thrown when Resource is not found
 * @author Narendra Korrapati
 *
 */
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 680142689446054851L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
