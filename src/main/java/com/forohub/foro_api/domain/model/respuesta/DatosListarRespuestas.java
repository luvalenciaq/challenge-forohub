package com.forohub.foro_api.domain.model.respuesta;

import java.time.LocalDateTime;

public record DatosListarRespuestas(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Boolean solucion
) {
    public DatosListarRespuestas(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion()
        );
    }
}
