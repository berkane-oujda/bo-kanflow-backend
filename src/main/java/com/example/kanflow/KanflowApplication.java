package com.example.kanflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.kanflow.config") // Ensure this is scanning the correct package

// @RestController
public class KanflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanflowApplication.class, args);
    }

    // @GetMapping("/status")
    // public String hello() {
    //     return "Server is up and running";
    // }
}
