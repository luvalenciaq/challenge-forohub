package com.forohub.foro_api.domain.model.respuesta;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByTopicoId(Long topicoId);
}