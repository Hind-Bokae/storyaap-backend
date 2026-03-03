package com.example.storyapp.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse>handelNotFound(
			ResourceNotFoundException exceptionDetails){
		ErrorResponse errorResponse=new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				exceptionDetails.getMessage()
		);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException errorDetails) {
		ErrorResponse error = new ErrorResponse(
				HttpStatus.FORBIDDEN.value(),
				errorDetails.getMessage()
		);
		return new  ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception errorDetails) {
		ErrorResponse error = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
			    "Unexpected server error"
		);
		return new  ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
