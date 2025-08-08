package com.forohub.foro_api.domain.model.topico.validaciones;

import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.topico.DatosRegistroTopico;
import com.forohub.foro_api.domain.model.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoDuplicado implements ValidadorTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosRegistroTopico datos) {
        boolean existe = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existe){
            throw new ValidacionException(
                    "Ya existe un tópico con el mismo título y mensaje."
            );
        }
    }

}
