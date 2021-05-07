package com.atriviss.raritycheck.controller_rest.exception;

public class InvalidOldPasswordException extends Exception {
    public InvalidOldPasswordException(String message) {
        super(message);
    }

    public InvalidOldPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
