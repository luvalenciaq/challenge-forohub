package com.forohub.foro_api.domain.model.topico;

import com.forohub.foro_api.domain.model.curso.CursoRepository;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import com.forohub.foro_api.domain.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Transactional
    public Topico registrarTopico(DatosRegistroTopico datos){
        System.out.println("ID del autor recibido: " + datos.idAutor());
        System.out.println("ID del curso recibido: " + datos.idCurso());
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        var topico = new Topico(datos, autor, curso);
        return topicoRepository.save(topico);
    }


    public Page<DatosListaTopicos> listar(Pageable paginacion){
        return topicoRepository.findAll(paginacion)
                .map(DatosListaTopicos::new);
    }


}
