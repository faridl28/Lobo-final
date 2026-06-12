package com.example.blog.config;

import com.example.blog.model.Categoria;
import com.example.blog.model.Post;
import com.example.blog.model.Usuario;
import com.example.blog.repository.CategoriaRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {
  private final PostRepository postRepository;
  private final UsuarioRepository usuarioRepository;
  private final CategoriaRepository categoriaRepository;

  public DataInitializer(PostRepository postRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
    this.postRepository = postRepository;
    this.usuarioRepository = usuarioRepository;
    this.categoriaRepository = categoriaRepository;
  }

  @PostConstruct
  @Transactional
  public void init(){
    Usuario u = new Usuario();
    u.setUsername("admin");
    u.setPassword("admin");
    u.setNombre("Administrador");
    u.setRole("ROLE_ADMIN");
    usuarioRepository.save(u);

    Categoria c = new Categoria();
    c.setNombre("General");
    c.setDescripcion("Categoría general");
    categoriaRepository.save(c);

    Post p = new Post();
    p.setTitulo("Primer post de ejemplo");
    p.setContenido("Contenido de ejemplo para el primer post.");
    p.setAutor(u);
    p.setCategoria(c);
    postRepository.save(p);
  }
}