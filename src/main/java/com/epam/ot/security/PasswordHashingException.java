package com.epam.ot.security;

public class PasswordHashingException extends RuntimeException {
    public PasswordHashingException() {
        super();
    }

    public PasswordHashingException(String message) {
        super(message);
    }

    public PasswordHashingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordHashingException(Throwable cause) {
        super(cause);
    }
}
