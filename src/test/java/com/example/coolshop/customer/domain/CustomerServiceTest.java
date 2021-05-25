package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService();
    }

    @Test
    void getCustomer() {
        //Given
        CustomerDomain customerDomain = CustomerDomain.builder()
                .fullName("Jørgen Petersen")
                .build();
        CustomerDomain registeredCustomer = customerService.registerCustomer(customerDomain);

        //when
        CustomerDomain result = customerService.getCustomer(registeredCustomer.getId());

        //then
        assertEquals(customerDomain.getFullName(), result.getFullName());
    }
}