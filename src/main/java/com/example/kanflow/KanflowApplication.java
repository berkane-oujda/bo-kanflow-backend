package com.example.kanflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.kanflow.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KanflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanflowApplication.class, args);
    }

}
