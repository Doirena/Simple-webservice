package com.dovile.simplewebservice.service;

import com.dovile.simplewebservice.domain.Customer;
import com.dovile.simplewebservice.entities.CustomerEntity;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public interface CustomerService {
    /**
     *
     * @return all list of customers
     * use {@link com.dovile.simplewebservice.repository.CustomerRepository}
     */
    List<CustomerEntity> getAllCustomers();

    /**
     *
     * @param id
     * @return customer, which clients wants by id
     * @throws ResourceNotFoundException
     */
    CustomerEntity getCustomer(Integer id) throws ResourceNotFoundException;

    /**
     *
     * @param customer
     * @return create new customer
     */
    CustomerEntity createCustomer(Customer customer);

    /**
     *
     * @param customer
     * @param id
     * @return update exist customer which clients choose and what he wants to change
     * @throws ResourceNotFoundException
     */
    CustomerEntity updateCustomer(Customer customer, Integer id) throws ResourceNotFoundException;

    /**
     *
     * @param id
     * @return delete customer which clients choose
     * @throws Exception
     */
    Map<String, Boolean> deleteCustomer(Integer id) throws Exception;
}
