package com.forohub.foro_api.domain.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //sirve para que se identifique como un servicio la clase y asi se comprende que tiene que cargarla

public class AutenticacionService implements UserDetailsService { //Es una interface que se encarga de hacer la carga de un usuario- obtiene los datos
    // y los pasa a un metodo para buscar los datos

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByCorreoElectronico(username); //tengo pedirle que busque las credenciales por medio del repository
    }
}