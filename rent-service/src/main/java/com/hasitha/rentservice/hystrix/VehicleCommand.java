package com.hasitha.rentservice.hystrix;


import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class VehicleCommand extends HystrixCommand<Vehicle> {

    RestTemplate restTemplate;
    int vehicleId;



    public VehicleCommand(RestTemplate restTemplate,int vehicleId) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate=restTemplate;
        this.vehicleId=vehicleId;
    }

    @Override
    protected Vehicle run() throws Exception {
        return restTemplate.getForObject("http://vehicle/services/vehicle/"+vehicleId,Vehicle.class);
    }

    //In case of our vehicle-service in not working empty vehicle object will be returned.
    @Override
    protected Vehicle getFallback() {
        return new Vehicle();
    }
}
