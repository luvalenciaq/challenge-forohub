package com.forohub.foro_api.domain.model.respuesta;


import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.respuesta.dto.DatosDetalleRespuesta;
import com.forohub.foro_api.domain.model.respuesta.dto.DatosListarRespuestas;
import com.forohub.foro_api.domain.model.respuesta.dto.DatosRegistroRespuesta;
import com.forohub.foro_api.domain.model.topico.TopicoRepository;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import jakarta.validation.Valid;
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
    private TopicoRepository topicoRepository;

    @Transactional
    public Respuesta registrar(Long idTopico, DatosRegistroRespuesta datos) {
        var usuario = getUsuarioActual();
        var topico = topicoRepository.findById(idTopico)
                .orElseThrow(() -> new ValidacionException("No existe un tópico con el ID indicado"));
        var respuesta = new Respuesta(
                null,
                datos.mensaje(),
                LocalDateTime.now(),
                topico,
                usuario,
                false
        );
        return respuestaRepository.save(respuesta);
    }

    public List<DatosListarRespuestas> listarPorTopico(Long idTopico) {
        var topico = topicoRepository.findById(idTopico)
                .orElseThrow(() -> new ValidacionException("No existe un tópico con el ID indicado"));
        return respuestaRepository.findByTopicoId(topico.getId())
                .stream()
                .map(DatosListarRespuestas::new)
                .toList();
    }

    @Transactional
    public DatosDetalleRespuesta actualizar(Long idTopico, Long idRespuesta, @Valid DatosRegistroRespuesta datos) {
        var respuesta = getRespuestaDeTopico(idRespuesta, idTopico);
        validarAutor(respuesta.getAutor().getId());
        respuesta.actualizar(datos);
        return new DatosDetalleRespuesta(respuesta);
    }

    @Transactional
    public void eliminar(Long idRespuesta, Long idTopico) {
        var respuesta = getRespuestaDeTopico(idRespuesta, idTopico);
        validarAutor(respuesta.getAutor().getId());
        respuestaRepository.deleteById(idRespuesta);
    }

    @Transactional
    public Respuesta toggleSolucion(Long idTopico, Long idRespuesta) {
        var respuesta = getRespuestaDeTopico(idRespuesta, idTopico);
        validarAutorTopico(respuesta.getTopico().getAutor().getId());
        respuesta.setSolucion(!respuesta.getSolucion());
        return respuesta;
    }

    // --- Métodos privados reutilizables ---

    private Usuario getUsuarioActual() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    private Respuesta getRespuestaDeTopico(Long idRespuesta, Long idTopico) {
        return respuestaRepository.findByIdAndTopicoId(idRespuesta, idTopico)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada para este tópico"));
    }

    private void validarAutor(Long idAutorRespuesta) {
        if (!idAutorRespuesta.equals(getUsuarioActual().getId())) {
            throw new ValidacionException("Solo el autor puede realizar esta acción");
        }
    }

    private void validarAutorTopico(Long idAutorTopico) {
        if (!idAutorTopico.equals(getUsuarioActual().getId())) {
            throw new ValidacionException("Solo el autor del tópico puede marcar una respuesta como solución");
        }
    }

}