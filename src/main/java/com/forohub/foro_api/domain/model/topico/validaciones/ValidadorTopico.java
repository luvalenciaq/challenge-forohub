package com.forohub.foro_api.domain.model.topico.validaciones;

import com.forohub.foro_api.domain.model.topico.dto.DatosRegistroTopico;

public interface ValidadorTopico {
    void validar(DatosRegistroTopico datos);
}
