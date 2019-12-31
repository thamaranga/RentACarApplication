package com.hasitha.rentcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RentCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentCloudConfigServerApplication.class, args);
    }

}
