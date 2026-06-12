package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensaje {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario emisor;

    @ManyToOne(optional = false)
    private Usuario receptor;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false, length = 5000)
    private String contenido;

    private boolean leido = false;

    private LocalDateTime enviadoEn = LocalDateTime.now();

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Usuario getEmisor(){return emisor;}
    public void setEmisor(Usuario emisor){this.emisor=emisor;}
    public Usuario getReceptor(){return receptor;}
    public void setReceptor(Usuario receptor){this.receptor=receptor;}
    public String getAsunto(){return asunto;}
    public void setAsunto(String asunto){this.asunto=asunto;}
    public String getContenido(){return contenido;}
    public void setContenido(String contenido){this.contenido=contenido;}
    public boolean isLeido(){return leido;}
    public void setLeido(boolean leido){this.leido=leido;}
    public LocalDateTime getEnviadoEn(){return enviadoEn;}
    public void setEnviadoEn(LocalDateTime enviadoEn){this.enviadoEn=enviadoEn;}
}