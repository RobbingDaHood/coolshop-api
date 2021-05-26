package com.example.coolshop.orders.api.exceptions;

import lombok.Getter;

@Getter
public class OrderDoesNotExistException extends RuntimeException {
    private Long oderID;

    public OrderDoesNotExistException(Long oderID) {
        super("Order " + oderID + " does not exist.");
        this.oderID = oderID;
    }
}
