package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.blog","com.example"})
public class BlogApplication {
  public static void main(String[] args){
    SpringApplication.run(BlogApplication.class,args);
  }
}
