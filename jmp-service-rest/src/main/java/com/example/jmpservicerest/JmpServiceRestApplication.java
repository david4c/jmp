package com.example.jmpservicerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class JmpServiceRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmpServiceRestApplication.class, args);
    }

}
