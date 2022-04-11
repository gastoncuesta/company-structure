package com.amazingco.companystructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.amazingco.companystructure.entity")
public class CompanyStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyStructureApplication.class, args);
    }

}
