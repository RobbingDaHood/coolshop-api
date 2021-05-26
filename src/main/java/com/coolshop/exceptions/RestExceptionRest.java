package com.coolshop.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestExceptionRest {
    private String exceptionName;
    private Long id;
    private String message;
}
