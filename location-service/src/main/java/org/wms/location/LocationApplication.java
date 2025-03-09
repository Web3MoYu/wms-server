package org.wms.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"org.wms.security", "org.wms.location"})
@EnableFeignClients(basePackages = "org.wms.api")
public class LocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationApplication.class, args);
    }
}
