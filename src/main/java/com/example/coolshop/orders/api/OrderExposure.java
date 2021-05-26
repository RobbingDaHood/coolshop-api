package com.example.coolshop.orders.api;

import com.example.coolshop.orders.api.mappers.OrderMapper;
import com.example.coolshop.orders.api.representation.OrderRepresentation;
import com.example.coolshop.orders.domain.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderExposure {
    private final OrderService orderService;

    public OrderExposure(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}")
    public OrderRepresentation getOrder(@PathVariable(value = "orderId") Long orderId) {
        return OrderMapper.mapFromDomain(orderService.getOrder(orderId));
    }

    @PostMapping("/orders")
    public OrderRepresentation postCustomer(@RequestBody OrderRepresentation orderRepresentation) {
        return OrderMapper.mapFromDomain(
                orderService.registerOrder(
                        OrderMapper.mapToDomain(orderRepresentation)
                )
        );
    }
}
