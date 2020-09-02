package com.dovile.simplewebservice.controllers;

import com.dovile.simplewebservice.domain.Customer;
import com.dovile.simplewebservice.entities.CustomerEntity;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;
import com.dovile.simplewebservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public List<CustomerEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerEntity getCustomerById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customer")
    public CustomerEntity createCustomer(@RequestBody @Valid Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public CustomerEntity updateCustomer(
            @PathVariable(value = "id") Integer id,
            @RequestBody @Valid Customer customer)
            throws ResourceNotFoundException {
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/customer/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer id)
            throws Exception {
        return customerService.deleteCustomer(id);
    }
}
