package com.forohub.foro_api.domain.model.topico;

import com.forohub.foro_api.domain.ValidacionException;
import com.forohub.foro_api.domain.model.curso.CursoRepository;
import com.forohub.foro_api.domain.model.topico.dto.DatosActualizarTopico;
import com.forohub.foro_api.domain.model.topico.dto.DatosDetalleTopico;
import com.forohub.foro_api.domain.model.topico.dto.DatosListaTopicos;
import com.forohub.foro_api.domain.model.topico.dto.DatosRegistroTopico;
import com.forohub.foro_api.domain.model.topico.validaciones.ValidadorTopico;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import com.forohub.foro_api.domain.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private List<ValidadorTopico> validadores;

    @Transactional
    public Topico registrarTopico(DatosRegistroTopico datos) {
        var autor = getUsuarioActual();
        validadores.forEach(v -> v.validar(datos));
        var curso = cursoRepository.getReferenceById(datos.idCurso());
        var topico = new Topico(datos, autor, curso);
        return topicoRepository.save(topico);
    }

    @Transactional
    public DatosDetalleTopico actualizar(Long id, DatosActualizarTopico datos) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico con ID \" + id + \"no existe"));
        validarAutor(topico.getAutor().getId());
        //validar duplicado (excepto consigo mismo)
        boolean duplicado = topicoRepository.existsByTituloAndMensajeAndIdNot(
                datos.titulo(), datos.mensaje(), id
        );
        if (duplicado) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje");
        }
            topico.actualizar(datos);
            return new DatosDetalleTopico(topico);
    }

    public Page<DatosListaTopicos> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion)
                .map(DatosListaTopicos::new);
    }

    @Transactional
    public void eliminar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("No existe un tópico con el id " + id));
            validarAutor(topico.getAutor().getId());
        topicoRepository.deleteById(id);
    }

    public DatosDetalleTopico detallar(Long id){
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado con id: " +id));
        return new DatosDetalleTopico(topico);
    }

    //--------- métodos reutilizables

    private Usuario getUsuarioActual() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    private void validarAutor(Long idAutorRespuesta) {
        if (!idAutorRespuesta.equals(getUsuarioActual().getId())) {
            throw new ValidacionException("Solo el autor puede realizar esta acción");
        }
    }

}
