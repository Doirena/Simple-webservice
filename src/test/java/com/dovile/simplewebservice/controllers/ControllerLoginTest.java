package com.dovile.simplewebservice.controllers;

import com.dovile.simplewebservice.entities.CustomerEntity;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;
import com.dovile.simplewebservice.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Before
    public void init() throws ResourceNotFoundException {
        CustomerEntity customerEntity = new CustomerEntity(1, "Anna", "Anna1");
        when(customerService.getCustomer(1)).thenReturn(customerEntity);
    }

    @WithMockUser("USER")
    @Test
    public void check_login_ok() throws Exception {

        mockMvc.perform(get("/api/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is("Anna")))
                .andExpect(jsonPath("$.last_name", is("Anna1")));
    }

    @Test
    public void without_login_error_401() throws Exception {
        mockMvc.perform(get("/api/customer/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}