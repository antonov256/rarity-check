package com.atriviss.raritycheck.controller_rest.controller_advice;

import com.atriviss.raritycheck.controller_rest.exception.ExceptionReport;
import com.atriviss.raritycheck.controller_rest.exception.OperationDeniedOnResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OperationDeniedOnResourceExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(OperationDeniedOnResourceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionReport categoryNotFoundHandler(OperationDeniedOnResourceException e) {
        return new ExceptionReport(e);
    }
}
