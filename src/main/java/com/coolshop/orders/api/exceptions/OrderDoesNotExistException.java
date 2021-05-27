package com.coolshop.orders.api.exceptions;

import lombok.Getter;

@Getter
public class OrderDoesNotExistException extends RuntimeException {
    private final Long oderID;

    public OrderDoesNotExistException(Long oderID) {
        super("Order " + oderID + " does not exist.");
        this.oderID = oderID;
    }
}
