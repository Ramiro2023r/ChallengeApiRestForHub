package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private String autor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico) {
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }
}