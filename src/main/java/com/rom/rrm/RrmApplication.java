package com.rom.rrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableSwagger2
public class RrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrmApplication.class, args);
    }

}
