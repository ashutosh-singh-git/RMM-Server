package com.rom.rrm.exception;

import com.rom.rrm.dto.ApiError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ApiError> handleException(DuplicateKeyException e) {
        return new ResponseEntity<ApiError>(new ApiError("Duplicate Submission", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
