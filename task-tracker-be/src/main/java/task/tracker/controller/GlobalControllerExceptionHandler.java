package task.tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import task.tracker.exception.ObjectMissingProperties;
import task.tracker.exception.ObjectNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@ExceptionHandler(ObjectMissingProperties.class)
	public ResponseEntity<Object> handleObjectMissingProperties(ObjectMissingProperties ex) {
		return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.NOT_FOUND);
	}
}
