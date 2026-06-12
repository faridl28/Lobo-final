package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService postService;
  public PostController(PostService postService){this.postService=postService;}

  @GetMapping
  public List<PostDto> list(){ return postService.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> get(@PathVariable Long id){
    PostDto dto = postService.findById(id);
    return dto==null? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<PostDto> create(@RequestBody Post post){
    PostDto dto = postService.create(post);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody Post post){
    PostDto dto = postService.update(id, post);
    return dto==null? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    postService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
