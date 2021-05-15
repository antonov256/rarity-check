package com.atriviss.raritycheck.controller_rest.controller_advice;

import com.atriviss.raritycheck.controller_rest.exception.ExceptionReport;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class MaxUploadSizeExceededExceptionAdvice {
    @ResponseBody
    @ExceptionHandler({MaxUploadSizeExceededException.class, FileSizeLimitExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionReport handleException(MaxUploadSizeExceededException e) {
        return new ExceptionReport(e.getClass().getSimpleName(), "File is too big");
    }
}
