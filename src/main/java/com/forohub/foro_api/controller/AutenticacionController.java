package com.forohub.foro_api.controller;

import com.forohub.foro_api.domain.model.usuario.DatosAutenticacion;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import com.forohub.foro_api.infra.security.DatosTokenJWT;
import com.forohub.foro_api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;//instancia de una clase que podemos usar para realizar el servicio de llamar al metodo del service

    @Autowired
    private TokenService tokenService;  //tiene que ser el de infra.security

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena()); //Este es para autenticar
        var autenticacion = manager.authenticate(authenticationToken); //el token son los datos del usuario en un formato especifico

        var  tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal()); //este token es el que devuelve
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}