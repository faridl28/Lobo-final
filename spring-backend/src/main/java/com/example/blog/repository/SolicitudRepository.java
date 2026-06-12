package com.example.blog.repository;

import com.example.blog.model.Solicitud;
import com.example.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findBySolicitante(Usuario solicitante);
    long countByEstado(Solicitud.Estado estado);
}