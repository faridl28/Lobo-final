package com.example.blog.service;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {
  @Test
  void findAllReturnsList(){
    PostRepository repo = Mockito.mock(PostRepository.class);
    Mockito.when(repo.findAll()).thenReturn(List.of(new Post()));
    PostService s = new PostService(repo);
    var list = s.findAll();
    assertNotNull(list);
    assertEquals(1, list.size());
  }
}
