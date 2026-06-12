package com.example.blog.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
  private Long id;
  private String titulo;
  private String contenido;
  private LocalDateTime creadoEn;
  private String autorUsername;
  private String categoriaNombre;
  private List<String> etiquetas;

  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getTitulo(){return titulo;}
  public void setTitulo(String titulo){this.titulo=titulo;}
  public String getContenido(){return contenido;}
  public void setContenido(String contenido){this.contenido=contenido;}
  public LocalDateTime getCreadoEn(){return creadoEn;}
  public void setCreadoEn(LocalDateTime creadoEn){this.creadoEn=creadoEn;}
  public String getAutorUsername(){return autorUsername;}
  public void setAutorUsername(String autorUsername){this.autorUsername=autorUsername;}
  public String getCategoriaNombre(){return categoriaNombre;}
  public void setCategoriaNombre(String categoriaNombre){this.categoriaNombre=categoriaNombre;}
  public List<String> getEtiquetas(){return etiquetas;}
  public void setEtiquetas(List<String> etiquetas){this.etiquetas=etiquetas;}
}
