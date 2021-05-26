package com.example.coolshop.orders.api;

import com.example.coolshop.customer.api.exceptionhandler.model.CustomerDoesNotExistExceptionRest;
import com.example.coolshop.orders.api.representation.OrderRepresentation;
import com.example.coolshop.orders.domain.OrderService;
import com.example.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.example.coolshop.orders.domain.model.OrderDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderExposure.class)
class OrderExposureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void getOrder() throws Exception {
        OrderDomain orderDomain = OrderDomain.builder()
                .id(22L)
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();
        when(orderService.getOrder(orderDomain.getId())).thenReturn(orderDomain);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/" + orderDomain.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        OrderRepresentation expectedOrderRepresentation = OrderRepresentation.builder()
                .id(orderDomain.getId())
                .itemIds(orderDomain.getItemIds())
                .discount(orderDomain.getDiscount())
                .customerId(orderDomain.getCustomerId())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedOrderRepresentation);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void getOrderCustomerDoesNotExist() throws Exception {
        OrderDomain orderDomain = OrderDomain.builder()
                .id(22L)
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();
        CustomerDoesNotExistException customerDoesNotExistException = new CustomerDoesNotExistException(orderDomain.getCustomerId());
        when(orderService.getOrder(orderDomain.getId()))
                .thenThrow(customerDoesNotExistException);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/" + orderDomain.getId()))
                .andExpect(status().is(406))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();


        CustomerDoesNotExistExceptionRest expectedException = CustomerDoesNotExistExceptionRest.builder()
                .customerId(21L)
                .message("Customer 21 does not exist.")
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedException);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void postCustomer() throws Exception {
        //Given
        OrderRepresentation orderRepresentation = OrderRepresentation.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();

        OrderDomain orderDomain = OrderDomain.builder()
                .id(22L)
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();

        ArgumentCaptor<OrderDomain> orderDomainArgumentCaptor = ArgumentCaptor.forClass(OrderDomain.class);
        when(orderService.registerOrder(orderDomainArgumentCaptor.capture())).thenReturn(orderDomain);

        //When and then
        MvcResult mvcResult = mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderRepresentation)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        OrderRepresentation expectedOrderRepresentation = OrderRepresentation.builder()
                .id(orderDomain.getId())
                .itemIds(orderDomain.getItemIds())
                .discount(orderDomain.getDiscount())
                .customerId(orderDomain.getCustomerId())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedOrderRepresentation);
        assertEquals(expectedJson, resultJson);

        OrderDomain nonRegisteredOrderDomain = OrderDomain.builder()
                .itemIds(orderRepresentation.getItemIds())
                .discount(orderRepresentation.getDiscount())
                .customerId(orderRepresentation.getCustomerId())
                .build();
        assertEquals(nonRegisteredOrderDomain, orderDomainArgumentCaptor.getValue());
    }
}