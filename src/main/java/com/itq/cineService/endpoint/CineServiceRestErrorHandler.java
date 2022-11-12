package com.itq.cineService.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CineServiceRestErrorHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {		
		String mensaje = "Solicitud JSON mal construida";
		ErrorResponse error = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, mensaje, ex);
		
		logger.error("ERROR: " + mensaje + "\n" + error.getMessage() + "\n" + error.getDebugMessage());
		
		return new ResponseEntity<>(error, error.getStatus());
	}

}
