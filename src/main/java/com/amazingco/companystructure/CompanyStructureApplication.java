package com.amazingco.companystructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CompanyStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyStructureApplication.class, args);
    }

}
