package com.dovile.simplewebservice.mapper;

import com.dovile.simple_web_service.CustomerInfo;
import com.dovile.simplewebservice.entities.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */

@Component
public class CustomerInfoMapper {

    public CustomerInfo mapToCustomerResponse(CustomerEntity customerEntity) {
        return new CustomerInfo(customerEntity.getId(), customerEntity.getFirst_name(), customerEntity.getLast_name());
    }
}