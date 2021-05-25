package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    public CustomerDomain getCustomer(String id) {
        return new CustomerDomain(id);
    }
}
