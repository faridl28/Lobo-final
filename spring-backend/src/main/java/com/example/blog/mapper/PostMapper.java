package com.example.blog.mapper;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import java.util.stream.Collectors;

public class PostMapper {
  public static PostDto toDto(Post p){
    if(p==null) return null;
    PostDto dto = new PostDto();
    dto.setId(p.getId());
    dto.setTitulo(p.getTitulo());
    dto.setContenido(p.getContenido());
    dto.setCreadoEn(p.getCreadoEn());
    dto.setAutorUsername(p.getAutor()!=null? p.getAutor().getUsername():null);
    dto.setCategoriaNombre(p.getCategoria()!=null? p.getCategoria().getNombre():null);
    if(p.getEtiquetas()!=null){
      dto.setEtiquetas(p.getEtiquetas().stream().map(t->t.getNombre()).collect(Collectors.toList()));
    }
    return dto;
  }
}
