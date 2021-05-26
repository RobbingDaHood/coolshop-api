package com.example.coolshop.orders.persistance;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.persistance.CustomerRepositoryImpl;
import com.example.coolshop.customer.persistance.entities.CustomerEntity;
import com.example.coolshop.orders.domain.model.OrderDomain;
import com.example.coolshop.orders.persistance.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderRepositoryImplTest {
    private final OrderCrudRepository orderCrudRepository = mock(OrderCrudRepository.class);

    private OrderRepositoryImpl orderRepository;

    @BeforeEach
    void initUseCase() {
        orderRepository = new OrderRepositoryImpl(orderCrudRepository);
    }

    @Test
    void getById() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(22L)
                .itemIds("22;23")
                .discount(200)
                .customerId(21L)
                .build();

        when(orderCrudRepository.findById(orderEntity.getId())).thenReturn(java.util.Optional.of(orderEntity));

        OrderDomain result = orderRepository.getById(orderEntity.getId());

        assertEquals(orderEntity.getId(), result.getId());
        assertEquals(orderEntity.getDiscount(), result.getDiscount());
        assertEquals(orderEntity.getCustomerId(), result.getCustomerId());
        assertEquals(List.of(22L, 23L), result.getItemIds());
    }

    @Test
    void store() {
        OrderDomain customerDomain = OrderDomain.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();;
        OrderEntity customerEntity = OrderEntity.builder()
                .itemIds("22;23")
                .discount(200)
                .customerId(21L)
                .build();;
        OrderEntity registeredCustomerEntity = OrderEntity.builder()
                .id(22L)
                .itemIds("22;23")
                .discount(200)
                .customerId(21L)
                .build();

        when(orderCrudRepository.save(eq(customerEntity))).thenReturn(registeredCustomerEntity);

        OrderDomain result = orderRepository.store(customerDomain);

        assertEquals(registeredCustomerEntity.getId(), result.getId());
        assertEquals(customerDomain.getDiscount(), result.getDiscount());
        assertEquals(customerDomain.getCustomerId(), result.getCustomerId());
        assertEquals(customerDomain.getItemIds(), result.getItemIds());
    }
}