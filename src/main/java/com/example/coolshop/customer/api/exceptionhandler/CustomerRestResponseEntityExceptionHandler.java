package com.example.coolshop.customer.api.exceptionhandler;

import com.example.coolshop.customer.api.exceptionhandler.model.CustomerDoesNotExistExceptionRest;
import com.example.coolshop.customer.api.exceptions.CustomerDoesNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerRestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { CustomerDoesNotExistException.class })
    protected ResponseEntity<Object> handleConflict(
            CustomerDoesNotExistException ex, WebRequest request) {
        CustomerDoesNotExistExceptionRest body = CustomerDoesNotExistExceptionRest.builder()
                .customerId(ex.getCustomerId())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, body,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}