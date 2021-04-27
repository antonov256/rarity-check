package com.atriviss.raritycheck.controller_rest.exception;

public class SearchQueryException extends RuntimeException {
    public SearchQueryException(String message) {
        super(message);
    }

    public SearchQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
