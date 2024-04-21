package com.example.kanflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KanflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanflowApplication.class, args);
    }

    @GetMapping("/status")
    public String hello() {
        return "Server is up and running";
    }
}
