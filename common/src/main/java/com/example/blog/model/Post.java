package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  @Column(length=10000) private String contenido;
  private LocalDateTime creadoEn = LocalDateTime.now();

  @ManyToOne private Usuario autor;
  @ManyToOne private Categoria categoria;
  @ManyToMany
  @JoinTable(name="post_etiqueta",
    joinColumns=@JoinColumn(name="post_id"),
    inverseJoinColumns=@JoinColumn(name="etiqueta_id"))
  private List<Etiqueta> etiquetas;

  @OneToMany(mappedBy="post", cascade=CascadeType.ALL) private List<Comentario> comentarios;
  @OneToMany(mappedBy="post", cascade=CascadeType.ALL) private List<Multimedia> multimedia;
  @OneToMany(mappedBy="post", cascade=CascadeType.ALL) private List<Like> likes;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getTitulo(){return titulo;}
  public void setTitulo(String titulo){this.titulo=titulo;}
  public String getContenido(){return contenido;}
  public void setContenido(String contenido){this.contenido=contenido;}
  public LocalDateTime getCreadoEn(){return creadoEn;}
  public void setCreadoEn(LocalDateTime creadoEn){this.creadoEn=creadoEn;}
  public Usuario getAutor(){return autor;}
  public void setAutor(Usuario autor){this.autor=autor;}
  public Categoria getCategoria(){return categoria;}
  public void setCategoria(Categoria categoria){this.categoria=categoria;}
  public List<Etiqueta> getEtiquetas(){return etiquetas;}
  public void setEtiquetas(List<Etiqueta> etiquetas){this.etiquetas=etiquetas;}
}
