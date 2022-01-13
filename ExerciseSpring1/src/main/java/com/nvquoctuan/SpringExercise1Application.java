package com.nvquoctuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringExercise1Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringExercise1Application.class, args);
  }

}
