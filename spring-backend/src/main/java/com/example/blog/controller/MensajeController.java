package com.example.blog.controller;

import com.example.blog.dto.MensajeRequestDto;
import com.example.blog.model.Mensaje;
import com.example.blog.service.MensajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping
    public ResponseEntity<Mensaje> enviar(@Valid @RequestBody MensajeRequestDto dto, Principal principal) {
        Mensaje m = mensajeService.enviar(dto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @GetMapping("/bandeja-entrada")
    public ResponseEntity<List<Mensaje>> bandeja(Principal principal) {
        return ResponseEntity.ok(mensajeService.bandeja(principal.getName()));
    }

    @GetMapping("/enviados")
    public ResponseEntity<List<Mensaje>> enviados(Principal principal) {
        return ResponseEntity.ok(mensajeService.enviados(principal.getName()));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<Mensaje> marcarLeido(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(mensajeService.marcarLeido(id, principal.getName()));
    }

    @GetMapping("/no-leidos/count")
    public ResponseEntity<Map<String, Long>> noLeidos(Principal principal) {
        long count = mensajeService.contarNoLeidos(principal.getName());
        return ResponseEntity.ok(Map.of("count", count));
    }
}