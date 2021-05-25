package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.domain.model.CreateCustomerRequestDomain;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerService {
    private Map<String, CustomerDomain> stringCustomerDomainMap = new HashMap<>();

    public CustomerDomain getCustomer(String id) {
        return stringCustomerDomainMap.get(id);
    }

    public void addCustomer(CreateCustomerRequestDomain customerRequestDomain) {
        CustomerDomain customerDomain = CustomerDomain.builder()
                .id("22")
                .fullName(customerRequestDomain.getFullName())
                .build();

        stringCustomerDomainMap.put("22", customerDomain);
    }
}
