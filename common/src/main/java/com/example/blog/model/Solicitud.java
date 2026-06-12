package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Solicitud {

    public enum Tipo { SOPORTE, ACCESO, INFORMACION }
    public enum Estado { PENDIENTE, APROBADA, RECHAZADA }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario solicitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false, length = 5000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    private String observacion;

    private LocalDateTime creadoEn = LocalDateTime.now();

    private LocalDateTime resueltaEn;

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Usuario getSolicitante(){return solicitante;}
    public void setSolicitante(Usuario solicitante){this.solicitante=solicitante;}
    public Tipo getTipo(){return tipo;}
    public void setTipo(Tipo tipo){this.tipo=tipo;}
    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion=descripcion;}
    public Estado getEstado(){return estado;}
    public void setEstado(Estado estado){this.estado=estado;}
    public String getObservacion(){return observacion;}
    public void setObservacion(String observacion){this.observacion=observacion;}
    public LocalDateTime getCreadoEn(){return creadoEn;}
    public void setCreadoEn(LocalDateTime creadoEn){this.creadoEn=creadoEn;}
    public LocalDateTime getResueltaEn(){return resueltaEn;}
    public void setResueltaEn(LocalDateTime resueltaEn){this.resueltaEn=resueltaEn;}
}