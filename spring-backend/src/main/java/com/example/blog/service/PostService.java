package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.mapper.PostMapper;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
  private final PostRepository postRepository;

  public PostService(PostRepository postRepository){
    this.postRepository = postRepository;
  }

  public List<PostDto> findAll(){
    return postRepository.findAll().stream().map(PostMapper::toDto).collect(Collectors.toList());
  }

  public PostDto findById(Long id){
    return postRepository.findById(id).map(PostMapper::toDto).orElse(null);
  }

  public PostDto create(Post post){
    Post saved = postRepository.save(post);
    return PostMapper.toDto(saved);
  }

  public PostDto update(Long id, Post updated){
    return postRepository.findById(id).map(p->{
      p.setTitulo(updated.getTitulo());
      p.setContenido(updated.getContenido());
      return PostMapper.toDto(postRepository.save(p));
    }).orElse(null);
  }

  public void delete(Long id){
    postRepository.deleteById(id);
  }
}
