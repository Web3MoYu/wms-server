package org.wms.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"org.wms.security", "org.wms.location"})
public class LocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationApplication.class, args);
    }
}
