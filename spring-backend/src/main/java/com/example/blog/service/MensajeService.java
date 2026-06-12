package com.example.blog.service;

import com.example.blog.dto.MensajeRequestDto;
import com.example.blog.model.Mensaje;
import com.example.blog.model.Usuario;
import com.example.blog.repository.MensajeRepository;
import com.example.blog.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;

    public MensajeService(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Mensaje enviar(MensajeRequestDto dto, String emisorUsername) {
        Usuario emisor = usuarioRepository.findByUsername(emisorUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emisor no encontrado"));
        Usuario receptor = usuarioRepository.findByUsername(dto.getReceptorUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receptor no encontrado"));
        Mensaje m = new Mensaje();
        m.setEmisor(emisor);
        m.setReceptor(receptor);
        m.setAsunto(dto.getAsunto());
        m.setContenido(dto.getContenido());
        return mensajeRepository.save(m);
    }

    public List<Mensaje> bandeja(String username) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.findByReceptor(u);
    }

    public List<Mensaje> enviados(String username) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.findByEmisor(u);
    }

    public Mensaje marcarLeido(Long id, String username) {
        Mensaje m = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado"));
        if (!m.getReceptor().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes marcar mensajes de otros usuarios");
        }
        m.setLeido(true);
        return mensajeRepository.save(m);
    }

    public long contarNoLeidos(String username) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.countByReceptorAndLeidoFalse(u);
    }
}