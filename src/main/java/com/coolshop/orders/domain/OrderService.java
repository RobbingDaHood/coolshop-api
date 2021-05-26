package com.coolshop.orders.domain;

import com.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.coolshop.orders.domain.model.OrderDomain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private OrderPlugIn orderPlugIn;
    private CustomerPlugIn customerPlugIn;

    public OrderService(OrderPlugIn orderPlugIn, CustomerPlugIn customerPlugIn) {
        this.orderPlugIn = orderPlugIn;
        this.customerPlugIn = customerPlugIn;
    }

    public Optional<OrderDomain> getOrder(Long id) {
        return orderPlugIn.getById(id);
    }

    public OrderDomain registerOrder(OrderDomain domain) {
        if (customerPlugIn.getById(domain.getCustomerId()).isPresent()) {
            return orderPlugIn.store(domain);
        } else {
            throw new CustomerDoesNotExistException(domain.getCustomerId());
        }
    }
}
