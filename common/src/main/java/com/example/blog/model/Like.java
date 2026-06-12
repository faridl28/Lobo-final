package com.example.blog.model;

import jakarta.persistence.*;

@Entity(name="PostLike")
public class Like {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Usuario usuario;
  @ManyToOne private Post post;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
}
