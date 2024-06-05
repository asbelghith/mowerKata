package com.asb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KataMowerApplication {
    public static void main(String[] args) {
            SpringApplication.run(KataMowerApplication.class, args);
    }
}