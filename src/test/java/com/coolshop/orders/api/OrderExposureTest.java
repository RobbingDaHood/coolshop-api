package com.coolshop.orders.api;

import com.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.coolshop.orders.api.models.CustomerDoesNotExistExceptionRest;
import com.coolshop.orders.api.models.OrderDoesNotExistExceptionRest;
import com.coolshop.orders.api.representation.OrderRepresentation;
import com.coolshop.orders.domain.OrderService;
import com.coolshop.orders.domain.model.OrderDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

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
        when(orderService.getOrder(orderDomain.getId())).thenReturn(Optional.of(orderDomain));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/" + orderDomain.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.coolshop.v0.5+json;charset=UTF-8"))
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
    void getOrderThatDoesNotExist() throws Exception {
        Long orderId = 22L;
        when(orderService.getOrder(orderId)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/" + orderId))
                .andExpect(status().is(404))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        OrderDoesNotExistExceptionRest expectedException = OrderDoesNotExistExceptionRest.builder()
                .id(orderId)
                .message("Order 22 does not exist.")
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedException);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void postOrder() throws Exception {
        //Given
        OrderRepresentation orderRepresentation = OrderRepresentation.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();
        OrderDomain nonRegisteredOrderDomain = OrderDomain.builder()
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

        when(orderService.registerOrder(nonRegisteredOrderDomain)).thenReturn(orderDomain);

        //When and then
        MvcResult mvcResult = mockMvc.perform(post("/orders")
                .contentType("application/vnd.coolshop.v0.5+json")
                .content(objectMapper.writeValueAsString(orderRepresentation)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.coolshop.v0.5+json;charset=UTF-8"))
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
    void postOrderCustomerDoesNotExist() throws Exception {
        //Given
        OrderRepresentation orderRepresentation = OrderRepresentation.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();
        OrderDomain orderDomain = OrderDomain.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(21L)
                .build();

        CustomerDoesNotExistException customerDoesNotExistException = new CustomerDoesNotExistException(22L);
        when(orderService.registerOrder(orderDomain)).thenThrow(customerDoesNotExistException);

        //When and then
        MvcResult mvcResult = mockMvc.perform(post("/orders")
                .contentType("application/vnd.coolshop.v0.5+json")
                .content(objectMapper.writeValueAsString(orderRepresentation)))
                .andExpect(status().is(406))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        CustomerDoesNotExistExceptionRest expectedException = CustomerDoesNotExistExceptionRest.builder()
                .id(22L)
                .message("Customer 22 does not exist.")
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedException);
        assertEquals(expectedJson, resultJson);
    }
}