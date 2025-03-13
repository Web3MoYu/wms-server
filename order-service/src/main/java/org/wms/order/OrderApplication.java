package org.wms.order;

import org.apache.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.wms.security", "org.wms.order"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.wms.api")
@EnableAutoDataSourceProxy
public class OrderApplication {
    private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
