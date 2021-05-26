package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.OrderDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private final OrderRepository OrderRepository = mock(OrderRepository.class);

    private OrderService OrderService;

    @BeforeEach
    void initUseCase() {
        OrderService = new OrderService(OrderRepository);
    }


    @Test
    void getOrder() {
        //Given
        OrderDomain registeredOrder = OrderDomain.builder()
                .id(22L)
                .build();
        when(OrderRepository.getById(registeredOrder.getId())).thenReturn(registeredOrder);

        //when
        OrderDomain result = OrderService.getOrder(registeredOrder.getId());

        //then
        assertEquals(registeredOrder, result);
    }

    @Test
    void storeOrder() {
        //Given
        OrderDomain Order = OrderDomain.builder().build();
        when(OrderRepository.store(Order)).thenReturn(Order);

        //when
        OrderDomain result = OrderService.registerOrder(Order);

        //then
        assertEquals(Order, result);
    }

}