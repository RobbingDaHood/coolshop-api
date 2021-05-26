package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    private Map<String, CustomerDomain> stringCustomerDomainMap = new HashMap<>();

    public CustomerDomain getCustomer(String id) {
        return stringCustomerDomainMap.get(id);
    }

    public CustomerDomain registerCustomer(CustomerDomain customerDomain) {
        CustomerDomain registeredCustomerDomain = CustomerDomain.builder()
                .id("22")
                .fullName(customerDomain.getFullName())
                .build();

        stringCustomerDomainMap.put("22", registeredCustomerDomain);

        return registeredCustomerDomain;
    }
}
