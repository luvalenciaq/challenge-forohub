package com.forohub.foro_api.controller;

import com.forohub.foro_api.domain.model.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
