package com.wenance.weapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class CoinExceptionHandler {
	
	@ExceptionHandler(ServerWebInputException.class)
	public ResponseEntity<CoinError> serverWebInputException(ServerWebInputException ex) {
		ex.printStackTrace();
		CoinError coinError = new CoinError(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST, 
				ex.getReason(), 
				ex.getMessage());

		return new ResponseEntity<CoinError>(coinError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CoinError> globalExceptionHandler(Exception ex) {
		ex.printStackTrace();
		CoinError coinError = new CoinError(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ex.getMessage(), 
				ex.getMessage());

		return new ResponseEntity<CoinError>(coinError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
