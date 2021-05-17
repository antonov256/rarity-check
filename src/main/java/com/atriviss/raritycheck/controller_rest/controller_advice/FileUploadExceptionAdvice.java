package com.atriviss.raritycheck.controller_rest.controller_advice;

import com.atriviss.raritycheck.controller_rest.exception.ExceptionReport;
import com.atriviss.raritycheck.controller_rest.exception.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FileUploadExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionReport handleException(FileUploadException e) {
        return new ExceptionReport(e);
    }
}
