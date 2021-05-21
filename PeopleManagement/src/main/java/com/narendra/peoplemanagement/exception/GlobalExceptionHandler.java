package com.narendra.peoplemanagement.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * To handle exceptions raised in the application globally.
 * @author Narendra Korrapati
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;

	/**
	 * Handles the {@link ResourceNotFoundException} and prepares the appropriate response with status code.
	 * @param rnfe
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
		
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(rnfe.getMessage());
		errorDetail.setMessage(rnfe.getClass().getName());
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles the {@link MethodArgumentNotValidException} and prepares the appropriate response with status code.
	 * This exception is raised when method arguments are not valid
	 * @param rnfe
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		
		errorDetail.setTitle("Validation Failed");
		errorDetail.setDetail("Input validation failed");
		errorDetail.setMessage(manve.getClass().getName());
		
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(messageSource.getMessage(fe, request.getLocale()));
			validationErrorList.add(validationError);
		}
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}
	
	// TODO: Override rest of the methods of ResponseEntityExceptionHandler to
	// generate custom response when there is an exception raised by Spring
	
}
