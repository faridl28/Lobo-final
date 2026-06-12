package com.example.blog.repository;

import com.example.blog.model.Mensaje;
import com.example.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByReceptor(Usuario receptor);
    List<Mensaje> findByEmisor(Usuario emisor);
    long countByReceptorAndLeidoFalse(Usuario receptor);
}