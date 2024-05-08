package com.inventivtest.dreamCaseApp.exceptions.Handler;

import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseNotFoundException;
import com.inventivtest.dreamCaseApp.exceptions.ExceptionResponse;
import com.inventivtest.dreamCaseApp.exceptions.userException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(DreamCaseNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handContratNotFoundException(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handUserNotFoundException(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN );
    }


}
