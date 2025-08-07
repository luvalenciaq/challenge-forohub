package com.forohub.foro_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //para decirle que va a modificar las configuracions de spring security
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean //para que spring reconozca que esa clase tiene que ser cargada y esta disponible para que se pueda leer
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll()
                            //Esto es para la documentacion de la api
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                            .permitAll()
                            //req.requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN"); //solo admin puede borrar
                            //req.requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN"); //solo admin puede borrar
                            .anyRequest()
                            .authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager autenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
