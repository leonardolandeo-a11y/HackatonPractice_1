package com.example.hackatonpractice_1;

import com.example.hackatonpractice_1.exception.BusinessException;
import com.example.hackatonpractice_1.exception.DuplicateResourceException;
import com.example.hackatonpractice_1.exception.ErrorResponse;
import com.example.hackatonpractice_1.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                "Not_found",
                ex.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                "Conflict",
                ex.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                "Bad_request",
                ex.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
