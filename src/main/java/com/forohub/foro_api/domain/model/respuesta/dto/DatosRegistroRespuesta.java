package com.forohub.foro_api.domain.model.respuesta.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje) {
}
