package com.atriviss.raritycheck.controller_rest.controller_advice;

import com.atriviss.raritycheck.controller_rest.exception.ExceptionReport;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccessDeniedExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionReport handleException(AccessDeniedException e) {
        return new ExceptionReport(e.getClass().getSimpleName(), "Access denied");
    }
}
