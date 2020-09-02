package com.dovile.simplewebservice.service;

import com.dovile.simplewebservice.domain.Customer;
import com.dovile.simplewebservice.entities.CustomerEntity;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;
import com.dovile.simplewebservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomers() {
        List<CustomerEntity> entityList = new ArrayList<CustomerEntity>();
        entityList.add(new CustomerEntity(1, "Anna", "Anna1"));
        entityList.add(new CustomerEntity(2, "John", "John2"));
        entityList.add(new CustomerEntity(3, "Tom", "Tom3"));
        when(customerRepository.findAll()).thenReturn(entityList);

        List<CustomerEntity> expectedCustomerList = customerService.getAllCustomers();

        assertEquals(3, expectedCustomerList.size());
        assertEquals(entityList.get(0).getFirst_name(), expectedCustomerList.get(0).getFirst_name());
        assertEquals(entityList.get(1).getLast_name(), expectedCustomerList.get(1).getLast_name());
        assertEquals(entityList.get(2).getFirst_name(), expectedCustomerList.get(2).getFirst_name());
    }

    @Test
    public void getCustomer() throws ResourceNotFoundException {
        Integer id = 1;
        CustomerEntity customerEntity = new CustomerEntity(id, "Anna", "Anna1");
        when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(customerEntity));

        CustomerEntity expectedCustomer = customerService.getCustomer(id);

        assertEquals(customerEntity.getFirst_name(), expectedCustomer.getFirst_name());
    }

    @Test
    public void getCustomer_Bad_ID() {
        Integer id = 1;
        try {
            CustomerEntity expectedCustomer = customerService.getCustomer(id);
        } catch (ResourceNotFoundException e) {
            assertEquals("Customer not found on: " + id, e.getMessage());
        }
    }

    @Test
    public void createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity(null, "Anna", "Anna1");
        given(customerRepository.save(customerEntity)).willAnswer(invocation -> invocation.getArgument(1));

        CustomerEntity expectedCustomer = customerService.createCustomer(new Customer("Anna", "Anna1"));

        assertThat(expectedCustomer).isNotNull();
        assertEquals(customerEntity.getFirst_name(), expectedCustomer.getFirst_name());
        verify(customerRepository).save(any(CustomerEntity.class));
    }

    @Test
    public void updateCustomer() throws ResourceNotFoundException {
        Integer id = 1;
        CustomerEntity customerEntity = new CustomerEntity(id, "Anna", "Anna1");
        when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(customerEntity));
        customerEntity.setFirst_name("Tom");
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        CustomerEntity expectedCustomer = customerService.updateCustomer(new Customer("Tom", "Anna1"), id);

        verify(customerRepository, times(1)).save(customerEntity);
        assertThat(expectedCustomer).isNotNull();
        assertEquals(customerEntity.getFirst_name(), expectedCustomer.getFirst_name());
    }

    @Test
    public void deleteCustomer_test() throws Exception {
        Integer id = 1;
        CustomerEntity customerEntity = new CustomerEntity(id, "Anna", "Anna1");
        when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(customerEntity));

        final Map<String, Boolean> result = customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).delete(customerEntity);
        assertEquals(true, result.containsKey("deleted"));
    }
}