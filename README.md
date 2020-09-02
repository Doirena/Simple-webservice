# Simple-webservice
Simple webservice which has rest and soap implementations. 
Also this service has basic authentication with username: <b>user</b> and password: <b> password</b>.  
Rest implementation has methods: <i>getAllCustomers, getCustomerById, createCustomer, updateCustomer, deleteCustomer</i>.  
Soap implementation has methods: <i>getAllCustomers, updateCustomer</i>.

## Running application
H2 in-memory database is used in this project.Data compilated from data.sql. 
### Compile and run application:
####  Rest:
http://localhost:8080/api/customer
#### Soap:
WSDL: http://localhost:8080/soapws/customers.wsdl  
If POSTMAN is used: http://localhost:8080/soapws  
<b>For GET method:</b>
```<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:art="http://dovile.com/simple-web-service">
        <soapenv:Header>
     <wsse:Security soapenv:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
         <wsse:UsernameToken>
             <wsse:Username>user</wsse:Username>
             <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">password</wsse:Password>
         </wsse:UsernameToken>
     </wsse:Security>
 </soapenv:Header>
    <soapenv:Body>
       <art:getAllCustomersRequest/>
    </soapenv:Body>
 </soapenv:Envelope>```
 ```
 <b>For PUT method:</b>
 ```<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:art="http://dovile.com/simple-web-service">
     <soapenv:Header>
      <wsse:Security soapenv:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
          <wsse:UsernameToken>
              <wsse:Username>user</wsse:Username>
              <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">password</wsse:Password>
          </wsse:UsernameToken>
      </wsse:Security>
  </soapenv:Header>
     <soapenv:Body>
        <art:updateCustomerRequest>
          <art:customerId>1</art:customerId>
          <art:first_name>Tom</art:first_name>
          <art:last_name>Tailor</art:last_name>
        </art:updateCustomerRequest>
     </soapenv:Body>
  </soapenv:Envelope>```