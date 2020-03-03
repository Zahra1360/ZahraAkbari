package com.asellion.product.controller;

import com.asellion.product.exception.AsellionServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handles all errors that are in the form of a AsellionServiceException.class
     *
     * @param exception the cause
     * @return an instance of ErrorMsg with an appropriate status code
     */
    @ExceptionHandler(AsellionServiceException.class)
    public ResponseEntity<ErrorMsg> handleAsellionServiceException(AsellionServiceException exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception.getErrorCode().isPresent()) {
            httpStatus = exception.getErrorCode().get().getValue();
        }
        return new ResponseEntity<>(new ErrorMsg(httpStatus.value(), exception.getMessage()), httpStatus);
    }

    /**
     * Handles all errors that are in the form of a Exception.class
     *
     * @param exception the cause
     * @return an instance of ErrorMsg with an appropriate status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> handleOtherExceptions(Exception exception) {
        HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;
        return new ResponseEntity<>(new ErrorMsg(httpStatus.value(), exception.getMessage()), httpStatus);
    }


}
