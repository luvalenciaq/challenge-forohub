package com.forohub.foro_api.controller;

import com.forohub.foro_api.domain.model.respuesta.DatosDetalleRespuesta;
import com.forohub.foro_api.domain.model.respuesta.DatosRegistroRespuesta;
import com.forohub.foro_api.domain.model.respuesta.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos/{idTopico}/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> registrar(@PathVariable Long idTopico, @RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriBuilder) {
        var respuesta = respuestaService.registrar(idTopico, datos);
        var datosRespuesta = new DatosDetalleRespuesta(respuesta);
        var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuesta);
    }
}
