package com.forohub.foro_api.domain.model.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion
) {
    public DatosDetalleTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion());
    }
}
