package com.banking.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
		
		@ExceptionHandler(exception = UserNotFoundException.class)
		public ResponseEntity<ErrorMessage> UserNotFoundException(UserNotFoundException ex){
			ErrorMessage msg = new ErrorMessage(ex.getMessage(), new Date(), HttpStatus.NOT_FOUND.name());
			return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		}
		
		
		@ExceptionHandler(UserAlreadyExistsException.class)
	    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409
	    }
		
		@ExceptionHandler(AccountNotFoundException.class)
		public  ResponseEntity<String> AccountNotFoundException(AccountNotFoundException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT); //204
		}

	}


