package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerService {
    private Map<String, CustomerDomain> stringCustomerDomainMap = new HashMap<>();

    public CustomerDomain getCustomer(String id) {
        return stringCustomerDomainMap.get(id);
    }

    public void addCustomer(CustomerDomain customerDomain) {
        stringCustomerDomainMap.put(customerDomain.getId(), customerDomain);
    }
}
