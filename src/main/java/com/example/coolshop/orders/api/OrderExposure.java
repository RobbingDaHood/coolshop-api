package com.example.coolshop.orders.api;

import com.example.coolshop.orders.api.exceptions.OrderDoesNotExistException;
import com.example.coolshop.orders.api.mappers.OrderMapper;
import com.example.coolshop.orders.api.representation.OrderRepresentation;
import com.example.coolshop.orders.domain.OrderService;
import com.example.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.example.coolshop.orders.domain.model.OrderDomain;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderExposure {
    private final OrderService orderService;

    public OrderExposure(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}")
    public OrderRepresentation getOrder(@PathVariable(value = "orderId") Long orderId) {
        Optional<OrderDomain> order = orderService.getOrder(orderId);

        if (order.isPresent()) {
            return OrderMapper.mapFromDomain(order.get());
        } else {
            throw new OrderDoesNotExistException(orderId);
        }
    }

    @PostMapping("/orders")
    public OrderRepresentation postOrder(@RequestBody OrderRepresentation orderRepresentation) {
        return OrderMapper.mapFromDomain(
                orderService.registerOrder(
                        OrderMapper.mapToDomain(orderRepresentation)
                )
        );
    }
}
