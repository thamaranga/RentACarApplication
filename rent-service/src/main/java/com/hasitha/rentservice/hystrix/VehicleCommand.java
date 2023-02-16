package com.hasitha.rentservice.hystrix;


import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.hasitha.rentservice.service.impl.RentServiceImpl;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class VehicleCommand extends HystrixCommand<Vehicle> {
    Logger logger= LoggerFactory.getLogger(VehicleCommand.class);
    RestTemplate restTemplate;
    int vehicleId;



    public VehicleCommand(RestTemplate restTemplate,int vehicleId) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate=restTemplate;
        this.vehicleId=vehicleId;
    }

    @Override
    protected Vehicle run() throws Exception {
        logger.info("Inside run method of VehicleCommand");
        return restTemplate.getForObject("http://vehicle/services/vehicle/"+vehicleId,Vehicle.class);
    }

    //In case of our vehicle-service in not working empty vehicle object will be returned.
    @Override
    protected Vehicle getFallback() {
        logger.info("Inside getFallback method of VehicleCommand");
        return new Vehicle();
    }
}
