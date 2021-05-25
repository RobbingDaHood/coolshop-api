package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    public Customer getCustomer(String id) {
        return new Customer(id);
    }
}
