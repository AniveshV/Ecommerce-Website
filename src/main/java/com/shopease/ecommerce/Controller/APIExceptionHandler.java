package com.shopease.ecommerce.Controller;

import com.shopease.ecommerce.Exception.ResourceExistsException;
import com.shopease.ecommerce.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ResourceExistsException.class)
        public ResponseEntity<?> handleResourceExistsException(ResourceExistsException ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
                return new ResponseEntity<>("Method Arguments Not Valid Exception",HttpStatus.BAD_REQUEST);
        }

}
