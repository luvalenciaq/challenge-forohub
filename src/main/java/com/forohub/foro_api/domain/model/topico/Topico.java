package com.forohub.foro_api.domain.model.topico;

import com.forohub.foro_api.domain.model.curso.Curso;
import com.forohub.foro_api.domain.model.Respuesta;
import com.forohub.foro_api.domain.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name= "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EstadoTopico estado = EstadoTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datos) {
    }

    //si no hago la asignacion al intentar hacer la insercion en la tabla, quedan null
    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
    }
}
