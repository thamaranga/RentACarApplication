package com.hasitha.rentservice.hystrix;


import com.hasitha.rentcloud.model.customer.Customer;
import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class CustomerCommand extends HystrixCommand<Customer> {

    RestTemplate restTemplate;
    int customerId;



    public CustomerCommand(RestTemplate restTemplate, int customerId) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate=restTemplate;
        this.customerId=customerId;
    }

    @Override
    protected Customer run() throws Exception {
        return restTemplate.getForObject("http://customer/services/customer/"+customerId,Customer.class);
    }

    //In case of our customer-service is not working empty customer object will be returned.
    @Override
    protected Customer getFallback() {
        return new Customer();
    }
}
