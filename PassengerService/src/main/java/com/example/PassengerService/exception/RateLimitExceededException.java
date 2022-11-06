package com.example.PassengerService.exception;

public class RateLimitExceededException extends PassengerException {
    String errorCode;

    public RateLimitExceededException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}