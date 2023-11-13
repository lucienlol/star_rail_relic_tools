package com.example.starrail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.starrail.dao")
public class StarrailApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarrailApplication.class, args);
    }

}
