package com.tiger.storeservice.exc;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StoreExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StoreNotFoundException.class)
	public final ResponseEntity<StoreException> StoreNotFoundException(Exception ex, WebRequest request)
			throws Exception {

		StoreException storeException = new StoreException(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<StoreException>(storeException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StoreAlreadyExistException.class)
	public final ResponseEntity<StoreException> StoreAlreadyExistException(Exception ex, WebRequest request)
			throws Exception {

		StoreException storeException = new StoreException(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<StoreException>(storeException, HttpStatus.CONFLICT);
	}
}
