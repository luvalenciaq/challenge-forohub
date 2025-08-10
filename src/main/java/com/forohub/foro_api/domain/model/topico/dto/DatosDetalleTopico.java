package com.forohub.foro_api.domain.model.topico.dto;

import com.forohub.foro_api.domain.model.topico.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String estado,
        String autor,
        String curso

) {
    public DatosDetalleTopico(Topico topico){
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getEstado().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
