package com.example.blog.service;

import com.example.blog.model.Solicitud;
import com.example.blog.model.Usuario;
import com.example.blog.repository.SolicitudRepository;
import com.example.blog.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitudService(SolicitudRepository solicitudRepository, UsuarioRepository usuarioRepository) {
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Solicitud radicar(String tipo, String descripcion, String username) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Solicitud s = new Solicitud();
        s.setSolicitante(u);
        s.setTipo(Solicitud.Tipo.valueOf(tipo.toUpperCase()));
        s.setDescripcion(descripcion);
        s.setEstado(Solicitud.Estado.PENDIENTE);
        return solicitudRepository.save(s);
    }

    public List<Solicitud> misSolicitudes(String username) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return solicitudRepository.findBySolicitante(u);
    }

    public List<Solicitud> todas() {
        return solicitudRepository.findAll();
    }

    public Solicitud aprobar(Long id, String observacion) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        s.setEstado(Solicitud.Estado.APROBADA);
        s.setObservacion(observacion);
        s.setResueltaEn(LocalDateTime.now());
        return solicitudRepository.save(s);
    }

    public Solicitud rechazar(Long id, String observacion) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        s.setEstado(Solicitud.Estado.RECHAZADA);
        s.setObservacion(observacion);
        s.setResueltaEn(LocalDateTime.now());
        return solicitudRepository.save(s);
    }

    public long contarPorEstado(Solicitud.Estado estado) {
        return solicitudRepository.countByEstado(estado);
    }

    public long contarTodas() {
        return solicitudRepository.count();
    }
}