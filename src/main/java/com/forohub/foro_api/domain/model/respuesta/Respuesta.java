package com.forohub.foro_api.domain.model.respuesta;

import com.forohub.foro_api.domain.model.respuesta.dto.DatosRegistroRespuesta;
import com.forohub.foro_api.domain.model.topico.Topico;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name= "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    private Topico topico;
    @ManyToOne
    private Usuario autor;
    private Boolean solucion = false;


    public void actualizar(DatosRegistroRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }


}
