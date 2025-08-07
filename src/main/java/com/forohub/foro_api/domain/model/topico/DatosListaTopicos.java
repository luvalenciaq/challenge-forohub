package com.forohub.foro_api.domain.model.topico;

import com.forohub.foro_api.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListaTopicos(
        String titulo,
        String mensaje,
        String estado,
        String autor,
        LocalDateTime fechaDeCreacion
) {
    public DatosListaTopicos(Topico topico){
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado().toString(),
                topico.getAutor().getNombre(),
                topico.getFechaDeCreacion());
    }
}
