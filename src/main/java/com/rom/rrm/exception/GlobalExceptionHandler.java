package com.rom.rrm.exception;

import com.rom.rrm.dto.ApiError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ApiError> handleException(DuplicateKeyException e) {
        return new ResponseEntity<>(new ApiError("Duplicate Submission", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handleException(ApplicationException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), ""), e.getStatusCode());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleException(HttpClientErrorException e) {
        return new ResponseEntity<>(new ApiError(e.getStatusText(), e.getMessage()), e.getStatusCode());
    }
}
