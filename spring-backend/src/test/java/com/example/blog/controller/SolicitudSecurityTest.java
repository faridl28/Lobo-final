package com.example.blog.controller;

import com.example.blog.BlogApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BlogApplication.class)
@AutoConfigureMockMvc
public class SolicitudSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postSolicitudSinAutenticacion_retorna401o403() throws Exception {
        mockMvc.perform(post("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\":\"SOPORTE\",\"descripcion\":\"test\"}"))
                .andExpect(status().is(org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.is(401),
                        org.hamcrest.Matchers.is(403)
                )));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void postSolicitudAutenticado_retorna201() throws Exception {
        mockMvc.perform(post("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\":\"SOPORTE\",\"descripcion\":\"test\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void aprobarSolicitudSinAdmin_retorna403() throws Exception {
        mockMvc.perform(put("/api/solicitudes/999/aprobar")
                        .param("observacion", "test"))
                .andExpect(status().isForbidden());
    }
}