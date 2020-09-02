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
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerId(customerEntity.getId());
        customerInfo.setFirstName(customerEntity.getFirst_name());
        customerInfo.setLastName(customerEntity.getLast_name());
        return  customerInfo;

        //Todo This will be better, but when maven generate CustomerInfo class, doesn't generate constructor with value, constructor have been added by hand, but if someone clean and install maven restart constructor and its disappear, program shows error
//        return new CustomerInfo(customerEntity.getId(), customerEntity.getFirst_name(), customerEntity.getLast_name());
    }
}