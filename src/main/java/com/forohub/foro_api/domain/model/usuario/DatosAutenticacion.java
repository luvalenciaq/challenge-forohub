package com.forohub.foro_api.domain.model.usuario;

import jakarta.validation.constraints.Email;

public record DatosAutenticacion(
       @Email String correoElectronico,
        String contrasena
) {
}
