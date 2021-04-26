package com.atriviss.raritycheck.controller_rest.controller_advice;

import com.amazonaws.services.databasemigrationservice.model.S3ResourceNotFoundException;
import com.atriviss.raritycheck.controller_rest.exception.ExceptionReport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class S3ResourceNotFoundExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(S3ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionReport badCredentialsHandler(S3ResourceNotFoundException e) {
        return new ExceptionReport(e);
    }
}
