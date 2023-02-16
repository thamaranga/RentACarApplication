package com.hasitha.rentcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//Below  annotation makes this application as a config server
@EnableConfigServer
public class RentCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentCloudConfigServerApplication.class, args);
    }

}
