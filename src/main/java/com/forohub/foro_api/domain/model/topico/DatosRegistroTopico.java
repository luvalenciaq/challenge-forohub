package com.forohub.foro_api.domain.model.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull Long idAutor,
    @NotNull Long idCurso)
     {
}
