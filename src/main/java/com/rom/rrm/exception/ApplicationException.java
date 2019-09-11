package com.rom.rrm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private String message;
    private HttpStatus statusCode;

    public ApplicationException(String message, HttpStatus statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApplicationException(String message, HttpStatus statusCode, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.statusCode = statusCode;
    }
}
