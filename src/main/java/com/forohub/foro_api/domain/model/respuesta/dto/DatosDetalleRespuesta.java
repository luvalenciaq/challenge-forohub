package com.forohub.foro_api.domain.model.respuesta.dto;

import com.forohub.foro_api.domain.model.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getAutor().getNombre(), respuesta.getSolucion());
    }
}
