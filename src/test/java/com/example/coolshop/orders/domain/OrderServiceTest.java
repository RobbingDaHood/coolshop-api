package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.example.coolshop.orders.domain.model.CustomerDomain;
import com.example.coolshop.orders.domain.model.OrderDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final CustomerRepository customerRepository = mock(CustomerRepository.class);

    private OrderService OrderService;

    @BeforeEach
    void initUseCase() {
        OrderService = new OrderService(orderRepository, customerRepository);
    }


    @Test
    void getOrder() {
        //Given
        OrderDomain registeredOrder = OrderDomain.builder()
                .id(22L)
                .build();
        when(orderRepository.getById(registeredOrder.getId())).thenReturn(Optional.of(registeredOrder));

        //when
        Optional<OrderDomain> result = OrderService.getOrder(registeredOrder.getId());

        //then
        assertTrue(result.isPresent());
        assertEquals(registeredOrder, result.get());
    }

    @Test
    void storeOrder() {
        //Given
        OrderDomain order = OrderDomain.builder()
                .customerId(22L)
                .build();
        when(orderRepository.store(order)).thenReturn(order);
        when(customerRepository.getById(order.getCustomerId())).thenReturn(Optional.of(CustomerDomain.builder().build()));

        //when
        OrderDomain result = OrderService.registerOrder(order);

        //then
        assertEquals(order, result);
    }

    @Test
    void storeOrderThrowCustomerDoesNotExistException() {
        //Given
        OrderDomain order = OrderDomain.builder()
                .customerId(22L)
                .build();
        when(orderRepository.store(order)).thenReturn(order);
        when(customerRepository.getById(order.getCustomerId())).thenReturn(Optional.empty());

        //when
        CustomerDoesNotExistException customerDoesNotExistException = assertThrows(CustomerDoesNotExistException.class, () -> OrderService.registerOrder(order));

        //then
        assertEquals(order.getCustomerId(), customerDoesNotExistException.getCustomerId());
    }

}