package com.lcdev25.bbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BBMApplication {

  public static void main(String[] args) {
    SpringApplication.run(BBMApplication.class, args);
  }
}
