package com.forohub.foro_api.domain.model.topico.dto;

import com.forohub.foro_api.domain.model.topico.Topico;

import java.time.LocalDateTime;

public record DatosListaTopicos(
        Long id,
        String titulo,
        String mensaje,
        String estado,
        String autor,
        String curso,
        LocalDateTime fechaDeCreacion
) {
    public DatosListaTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaDeCreacion());
    }
}
