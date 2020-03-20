package com.binge.lesiyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.binge.lesiyu.mapper")
public class LesiyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LesiyuApplication.class, args);
    }

}
