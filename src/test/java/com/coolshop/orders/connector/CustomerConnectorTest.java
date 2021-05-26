package com.coolshop.orders.connector;

import com.coolshop.customer.domain.CustomerService;
import com.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerConnectorTest {
    private CustomerService customerService = mock(CustomerService.class);

    private CustomerConnector customerConnector;

    @BeforeEach
    void initUseCase() {
        customerConnector = new CustomerConnector(customerService);
    }

    @Test
    void getById() {
        when(customerService.getCustomer(21L)).thenReturn(Optional.of(mock(CustomerDomain.class)));

        Optional<com.coolshop.orders.domain.model.CustomerDomain> customerDomain = customerConnector.getById(21L);

        assertTrue(customerDomain.isPresent());
    }

    @Test
    void getByIdEmpty() {
        when(customerService.getCustomer(21L)).thenReturn(Optional.empty());

        Optional<com.coolshop.orders.domain.model.CustomerDomain> customerDomain = customerConnector.getById(21L);

        assertFalse(customerDomain.isPresent());
    }
}