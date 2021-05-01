package com.atriviss.raritycheck.controller_rest.exception;

import lombok.Getter;

@Getter
public class ExceptionReport {
    private String error;
    private String message;

    public ExceptionReport(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public ExceptionReport(Throwable throwable) {
        this.error = throwable.getClass().getSimpleName();
        this.message = throwable.getMessage();
    }
}
