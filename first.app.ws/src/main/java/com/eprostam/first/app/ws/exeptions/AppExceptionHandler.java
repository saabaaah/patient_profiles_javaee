package com.eprostam.first.app.ws.exeptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
