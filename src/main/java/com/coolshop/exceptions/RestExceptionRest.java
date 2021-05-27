package com.coolshop.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestExceptionRest {
    private final String exceptionName;
    private final Long id;
    private final String message;
}
