package com.example.blog.controller;

import com.example.blog.BlogApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BlogApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {
  @Autowired
  private TestRestTemplate rest;

  @Test
  void getPostsEndpointWorks(){
    var res = rest.getForEntity("/api/posts", String.class);
    assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
  }
}
