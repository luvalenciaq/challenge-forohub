package com.forohub.foro_api.domain.model.respuesta;


import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.topico.TopicoRepository;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import com.forohub.foro_api.domain.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Respuesta registrar(Long idTopico, DatosRegistroRespuesta datos) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) authentication.getPrincipal();
        var topico = topicoRepository.getReferenceById(idTopico);

        var respuesta = new Respuesta(null, datos.mensaje(), LocalDateTime.now(), topico, usuario, false);
         return respuestaRepository.save(respuesta);
    }



    public List<DatosListarRespuestas> listarPorTopico(Long idTopico) {
        return respuestaRepository.findByTopicoId(idTopico)
                .stream()
                .map(DatosListarRespuestas::new)
                .toList();
    }

    @Transactional
    public DatosDetalleRespuesta actualizar(Long id, DatosRegistroRespuesta datos) {
        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var autor = (Usuario) authentication.getPrincipal();
        if (!respuesta.getAutor().getId().equals(autor.getId())) {
            throw new ValidacionException("Solo el autor puede editar esta respuesta");
        }
        respuesta.actualizar(datos);
        return new DatosDetalleRespuesta(respuesta);
    }
}

