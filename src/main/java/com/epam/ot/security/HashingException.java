package com.epam.ot.security;

public class HashingException extends RuntimeException {
    public HashingException() {
        super();
    }

    public HashingException(String message) {
        super(message);
    }

    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashingException(Throwable cause) {
        super(cause);
    }
}
