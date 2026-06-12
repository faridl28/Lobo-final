package com.example.blog.controller;

import com.example.blog.service.MensajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MensajeController.class)
public class MensajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MensajeService mensajeService;

    @Test
    @WithMockUser(username = "admin")
    void bandejaEntradaAutenticado_retorna200() throws Exception {
        when(mensajeService.bandeja(any())).thenReturn(List.of());
        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().isOk());
    }

    @Test
    void bandejaEntradaSinAutenticacion_retorna401o403() throws Exception {
        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().is(org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.is(401),
                        org.hamcrest.Matchers.is(403)
                )));
    }

    @Test
    @WithMockUser(username = "admin")
    void postMensajeCuerpoVacio_retorna400() throws Exception {
        mockMvc.perform(post("/api/mensajes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}