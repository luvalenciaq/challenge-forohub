package com.forohub.foro_api.domain.model.topico.validaciones;

import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.curso.CursoRepository;
import com.forohub.foro_api.domain.model.topico.DatosRegistroTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoExistente implements ValidadorTopico{

    @Autowired
    private CursoRepository cursoRepository;

    public void  validar(DatosRegistroTopico datos){
        if (!cursoRepository.existsById(datos.idCurso())){
            throw new ValidacionException("El curso con id " + datos.idCurso() + " no existe.");
        }
    }
}
