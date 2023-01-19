package com.company.vendingmachineportfolio;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEncryptableProperties
public class VendingMachinePortfolio {
    public static void main(String[] args) {
        SpringApplication.run(VendingMachinePortfolio.class, args);
    }
}
