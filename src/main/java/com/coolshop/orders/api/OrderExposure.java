package com.coolshop.orders.api;

import com.coolshop.orders.api.mappers.OrderMapper;
import com.coolshop.orders.api.exceptions.OrderDoesNotExistException;
import com.coolshop.orders.api.representation.OrderRepresentation;
import com.coolshop.orders.domain.OrderService;
import com.coolshop.orders.domain.model.OrderDomain;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Api(value="Order endpoint")
public class OrderExposure {
    private final OrderService orderService;

    public OrderExposure(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/orders/{orderId}",
    produces = "application/vnd.coolshop.v0.5+json;charset=UTF-8")
    public OrderRepresentation getOrder(@PathVariable(value = "orderId") Long orderId) {
        Optional<OrderDomain> order = orderService.getOrder(orderId);

        if (order.isPresent()) {
            return OrderMapper.mapFromDomain(order.get());
        } else {
            throw new OrderDoesNotExistException(orderId);
        }
    }

    @PostMapping(path = "/orders",
            produces = "application/vnd.coolshop.v0.5+json;charset=UTF-8",
            consumes = "application/vnd.coolshop.v0.5+json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRepresentation postOrder(@RequestBody OrderRepresentation orderRepresentation
    ) {
        return OrderMapper.mapFromDomain(
                orderService.registerOrder(
                        OrderMapper.mapToDomain(orderRepresentation)
                )
        );
    }

//    @PostMapping(path = "/orders",
//            produces = "application/vnd.coolshop.v0.5+json;charset=UTF-8",
//            consumes = "application/vnd.coolshop.v0.5+json;charset=UTF-8")
//    @ResponseStatus(HttpStatus.CREATED)
//    public OrderRepresentation postOrder(
//            @RequestBody OrderRepresentation orderRepresentation,
//            @PathVariable("customerId") Optional<Long> customerId
//    ) {
//        return OrderMapper.mapFromDomain(
//                orderService.registerOrder(
//                        OrderMapper.mapToDomain(orderRepresentation),
//                        customerId
//                )
//        );
//    }
}
