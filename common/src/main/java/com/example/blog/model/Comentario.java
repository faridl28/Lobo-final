package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comentario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length=2000) private String texto;
  private LocalDateTime creadoEn = LocalDateTime.now();

  @ManyToOne private Usuario autor;
  @ManyToOne private Post post;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getTexto(){return texto;}
  public void setTexto(String texto){this.texto=texto;}
  public LocalDateTime getCreadoEn(){return creadoEn;}
  public void setCreadoEn(LocalDateTime creadoEn){this.creadoEn=creadoEn;}
}
