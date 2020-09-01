package com.dovile.simplewebservice.service;

import com.dovile.simplewebservice.domain.Customer;
import com.dovile.simplewebservice.entities.CustomerEntity;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;
import com.dovile.simplewebservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final static Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getCustomer(Integer id) throws ResourceNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on: " + id));
        return customerEntity;
    }

    @Override
    public CustomerEntity createCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirst_name(customer.getFirst_name());
        customerEntity.setLast_name(customer.getLast_name());
        customerRepository.save(customerEntity);
        logger.info("Create new Customer");
        return customerEntity;
    }

    @Override
    public CustomerEntity updateCustomer(Customer customer, Integer id) throws ResourceNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on: " + id));
        if(customer.getFirst_name() !=null) {
            customerEntity.setFirst_name(customer.getFirst_name());
        }
        if(customer.getLast_name() != null){
            customerEntity.setLast_name(customer.getLast_name());
        }
        final CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
        logger.info("Update is successful");
        return updatedCustomer;
    }

    @Override
    public Map<String, Boolean> deleteCustomer(Integer id) throws Exception {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on: " + id));
        customerRepository.delete(customerEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
