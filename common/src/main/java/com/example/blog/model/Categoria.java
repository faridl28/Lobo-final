package com.example.blog.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Categoria {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String descripcion;

  @OneToMany(mappedBy = "categoria")
  private List<Post> posts;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getNombre(){return nombre;}
  public void setNombre(String nombre){this.nombre=nombre;}
  public String getDescripcion(){return descripcion;}
  public void setDescripcion(String descripcion){this.descripcion=descripcion;}
}
