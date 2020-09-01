package com.dovile.simplewebservice.endpoints;

import com.dovile.simple_web_service.CustomerInfo;
import com.dovile.simple_web_service.GetAllCustomersResponse;
import com.dovile.simple_web_service.UpdateCustomerRequest;
import com.dovile.simple_web_service.UpdateCustomerResponse;
import com.dovile.simplewebservice.domain.Customer;
import com.dovile.simplewebservice.exception.ResourceNotFoundException;
import com.dovile.simplewebservice.mapper.CustomerInfoMapper;
import com.dovile.simplewebservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@Endpoint
@AllArgsConstructor
public class CustomerEndpoint {
    private static final String NAMESPACE_URI = "http://dovile.com/simple-web-service";
    private CustomerService customerService;
    private CustomerInfoMapper customerInfoMapper;

    /**
     *
     * @return All Customers list
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCustomersRequest")
    @ResponsePayload
    public GetAllCustomersResponse getAllArticles() {
        GetAllCustomersResponse response = new GetAllCustomersResponse();

        List<CustomerInfo> customerInfoList=customerService.getAllCustomers().stream()
                .map(customerInfoMapper::mapToCustomerResponse)
                .collect(Collectors.toList());

        response.getCustomerInfo().addAll(customerInfoList);
        return response;
    }

    /**
     *
     * @param request
     * @return update Customer
     * @throws ResourceNotFoundException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCustomerRequest")
    @ResponsePayload
    public UpdateCustomerResponse updateCustomer(@RequestPayload UpdateCustomerRequest request) throws ResourceNotFoundException {
        Customer customer= new Customer(request.getFirstName(), request.getLastName());
        CustomerInfo customerInfo = customerInfoMapper
                .mapToCustomerResponse(customerService.updateCustomer(customer,request.getCustomerId()));

        UpdateCustomerResponse response = new UpdateCustomerResponse();
        response.setCustomerInfo(customerInfo);
        return response;
    }
}
