package com.aacid0.fugitiva.webapi.common.exception;

public class UnauthorizedTokenException extends RuntimeException {
    public UnauthorizedTokenException(String message) {
        super(message);
    }
}
