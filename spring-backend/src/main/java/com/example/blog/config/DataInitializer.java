package com.example.blog.config;

import com.example.blog.model.Categoria;
import com.example.blog.model.Post;
import com.example.blog.model.Usuario;
import com.example.blog.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {
  private final PostRepository postRepository;
  public DataInitializer(PostRepository postRepository){this.postRepository=postRepository;}

  @PostConstruct
  @Transactional
  public void init(){
    Usuario u = new Usuario();
    u.setUsername("admin");
    u.setPassword("admin");
    u.setNombre("Administrador");
    u.setRole("ROLE_ADMIN");

    Categoria c = new Categoria();
    c.setNombre("General");
    c.setDescripcion("Categoría general");

    Post p = new Post();
    p.setTitulo("Primer post de ejemplo");
    p.setContenido("Contenido de ejemplo para el primer post.");
    p.setAutor(u);
    p.setCategoria(c);

    postRepository.save(p);
  }
}
