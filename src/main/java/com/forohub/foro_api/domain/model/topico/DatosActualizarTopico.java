package com.forohub.foro_api.domain.model.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        String titulo,
        String mensaje
) {
}
