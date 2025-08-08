package com.forohub.foro_api.controller;

import com.forohub.foro_api.domain.model.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos){
        var topico = topicoService.registrarTopico(datos);
        var datosRespuesta = new DatosDetalleTopico(topico);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    public ResponseEntity actualizar(@RequestBody DatosActualizarTopico datos){
        var topicoActualizado = topicoService.actualizar(datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> listar(@PageableDefault(size = 10, sort = {"fechaDeCreacion"}) Pageable paginacion){
        var page = topicoService.listar(paginacion);
        return ResponseEntity.ok(page);
    }

}
