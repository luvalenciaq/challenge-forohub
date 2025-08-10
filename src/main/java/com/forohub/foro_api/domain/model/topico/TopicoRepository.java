package com.forohub.foro_api.domain.model.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);

}
