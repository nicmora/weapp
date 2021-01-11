package com.wenance.weapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice(annotations = RestController.class)
public class CoinExceptionHandler {

	@ExceptionHandler(ServerWebInputException.class)
	protected ResponseEntity<CoinError> handleServerWebInputException(ServerWebInputException e) {
		e.printStackTrace();
		return buildCoinError(HttpStatus.BAD_REQUEST, "Error al ingresar los datos");
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<CoinError> handleException(Exception e) {
		e.printStackTrace();
		return buildCoinError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en el servidor");
	}
	
	private ResponseEntity<CoinError> buildCoinError(HttpStatus status, String msg) {
		CoinError coinError = new CoinError(
				LocalDateTime.now(), 
				status, 
				"--- WEAPP-ERROR --- " + msg);
		
		return new ResponseEntity<CoinError>(coinError, status);
	}

}
