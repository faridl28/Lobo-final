package com.example.blog.controller;

import com.example.blog.model.Solicitud;
import com.example.blog.service.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<Solicitud> radicar(@RequestBody Map<String, String> body, Principal principal) {
        String tipo = body.get("tipo");
        String descripcion = body.get("descripcion");
        if (tipo == null || descripcion == null || tipo.isBlank() || descripcion.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Solicitud s = solicitudService.radicar(tipo, descripcion, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping("/mis-solicitudes")
    public ResponseEntity<List<Solicitud>> misSolicitudes(Principal principal) {
        return ResponseEntity.ok(solicitudService.misSolicitudes(principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> todas() {
        return ResponseEntity.ok(solicitudService.todas());
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Solicitud> aprobar(@PathVariable Long id, @RequestParam String observacion) {
        return ResponseEntity.ok(solicitudService.aprobar(id, observacion));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Solicitud> rechazar(@PathVariable Long id, @RequestParam String observacion) {
        return ResponseEntity.ok(solicitudService.rechazar(id, observacion));
    }
}