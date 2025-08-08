package com.forohub.foro_api.domain.model.topico.validaciones;

import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.topico.DatosRegistroTopico;
import com.forohub.foro_api.domain.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAutorExistente implements ValidadorTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosRegistroTopico datos){
        if (!usuarioRepository.existsById(datos.idAutor())){
            throw new ValidacionException("El autor con id " + datos.idAutor() + " no existe.");
        }
    }
}
