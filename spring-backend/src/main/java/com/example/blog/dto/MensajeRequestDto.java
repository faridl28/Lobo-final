package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;

public class MensajeRequestDto {
    @NotBlank
    private String receptorUsername;
    @NotBlank
    private String asunto;
    @NotBlank
    private String contenido;

    public String getReceptorUsername(){return receptorUsername;}
    public void setReceptorUsername(String receptorUsername){this.receptorUsername=receptorUsername;}
    public String getAsunto(){return asunto;}
    public void setAsunto(String asunto){this.asunto=asunto;}
    public String getContenido(){return contenido;}
    public void setContenido(String contenido){this.contenido=contenido;}
}