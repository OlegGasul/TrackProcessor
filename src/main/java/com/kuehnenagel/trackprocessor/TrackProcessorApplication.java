package com.kuehnenagel.trackprocessor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackProcessorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TrackProcessorApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
