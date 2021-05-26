package com.example.coolshop.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestExceptionRest {
    private String exceptionName = "CustomerDoesNotExistException";
    private Long id;
    private String message;
}
