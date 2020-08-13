package com.rahul.SpringRestfulJPA.rahulSpring.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rahul.SpringRestfulJPA.rahulSpring.post.PostAlreadyExistsException;
import com.rahul.SpringRestfulJPA.rahulSpring.post.PostNotFoundException;
import com.rahul.SpringRestfulJPA.rahulSpring.user.UserAlreadyExistsException;
import com.rahul.SpringRestfulJPA.rahulSpring.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req){
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(resp,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest req){
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(resp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExistsExceptions(Exception ex, WebRequest req){
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(resp,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public final ResponseEntity<Object> handlePostNotFoundExceptions(Exception ex, WebRequest req){
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(resp,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PostAlreadyExistsException.class)
	public final ResponseEntity<Object> handlePostAlreadyExistsExceptions(Exception ex, WebRequest req){
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(resp,HttpStatus.CONFLICT);
	}
	
}
