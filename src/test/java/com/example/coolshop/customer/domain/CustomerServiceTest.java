package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.domain.model.CreateCustomerRequestDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService();
    }

    @Test
    void getCustomer() {
        //Given
        CreateCustomerRequestDomain createCustomerRequestDomain = CreateCustomerRequestDomain.builder()
                .fullName("Jørgen Petersen")
                .build();
        customerService.addCustomer(createCustomerRequestDomain);

        //when
        CustomerDomain result = customerService.getCustomer("22");

        //then
        assertEquals(createCustomerRequestDomain.getFullName(), result.getFullName());
    }
}