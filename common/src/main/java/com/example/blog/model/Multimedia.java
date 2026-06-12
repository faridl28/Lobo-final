package com.example.blog.model;

import jakarta.persistence.*;

@Entity
public class Multimedia {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String url;
  private String tipo;

  @ManyToOne private Post post;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getUrl(){return url;}
  public void setUrl(String url){this.url=url;}
  public String getTipo(){return tipo;}
  public void setTipo(String tipo){this.tipo=tipo;}
}
