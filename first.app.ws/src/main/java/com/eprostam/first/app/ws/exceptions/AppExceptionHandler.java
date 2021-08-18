package com.eprostam.first.app.ws.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eprostam.first.app.ws.responses.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler {


	@ExceptionHandler(value= {UserException.class})
	public ResponseEntity<Object> HandleUserException(UserException ex, WebRequest req) {
		
		ErrorResponse response = new ErrorResponse(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> HandleOtherException(Exception ex, WebRequest req) {
		
		ErrorResponse response = new ErrorResponse(new Date(), ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> HandleOtherException(MethodArgumentNotValidException ex, WebRequest req) {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
}
