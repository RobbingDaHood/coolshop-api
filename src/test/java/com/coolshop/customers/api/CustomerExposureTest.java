package com.coolshop.customers.api;

import com.coolshop.customers.api.representation.CustomerRepresentation;
import com.coolshop.customers.domain.CustomerService;
import com.coolshop.customers.domain.model.CustomerDomain;
import com.coolshop.exceptions.RestExceptionRest;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerExposure.class)
class CustomerExposureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void getCustomer() throws Exception {
        CustomerDomain customerDomain = CustomerDomain.builder().id(22L).fullName("Jørgen Petersen").build();
        when(customerService.getCustomer(22L)).thenReturn(Optional.of(customerDomain));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/customers/22"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.coolshop.v1.0+json;charset=UTF-8"))
                .andReturn();

        CustomerRepresentation expectedCustomerRepresentation = CustomerRepresentation.builder()
                .id(customerDomain.getId())
                .fullName(customerDomain.getFullName())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerRepresentation);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void getCustomerMissingCustomer() throws Exception {
        when(customerService.getCustomer(22L)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/customers/22"))
                .andExpect(status().is(404))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        RestExceptionRest expectedException = RestExceptionRest.builder()
                .exceptionName("CustomerDoesNotExistException")
                .id(22L)
                .message("Customer 22 does not exist.")
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedException);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void postCustomer() throws Exception {
        //Given
        CustomerRepresentation customerRepresentation = CustomerRepresentation.builder()
                .fullName("Jørgen Petersen")
                .build();
        CustomerDomain customerDomain = CustomerDomain.builder()
                .fullName(customerRepresentation.getFullName())
                .build();

        CustomerDomain registeredCustomerDomain = CustomerDomain.builder()
                .id(22L)
                .fullName(customerRepresentation.getFullName())
                .build();

        when(customerService.registerCustomer(customerDomain)).thenReturn(registeredCustomerDomain);

        //When and then
        MvcResult mvcResult = mockMvc.perform(post("/customers")
                .contentType("application/vnd.coolshop.v1.0+json")
                .content(objectMapper.writeValueAsString(customerRepresentation)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.coolshop.v1.0+json;charset=UTF-8"))
                .andReturn();

        CustomerRepresentation expectedCustomerRepresentation = CustomerRepresentation.builder()
                .id(registeredCustomerDomain.getId())
                .fullName(registeredCustomerDomain.getFullName())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerRepresentation);
        assertEquals(expectedJson, resultJson);
    }
}