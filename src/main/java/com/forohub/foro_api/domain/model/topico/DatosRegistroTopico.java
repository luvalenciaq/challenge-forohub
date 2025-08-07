package com.forohub.foro_api.domain.model.topico;

public record DatosRegistroTopico(
    String titulo,
    String mensaje,
    Long idAutor,
    Long idCurso)
     {
}
