package com.example.blog.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Etiqueta {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;

  @ManyToMany(mappedBy = "etiquetas")
  private List<Post> posts;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getNombre(){return nombre;}
  public void setNombre(String nombre){this.nombre=nombre;}
}
