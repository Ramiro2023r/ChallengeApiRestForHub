package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        Long topicoId,
        String autor,
        LocalDateTime fechaCreacion,
        Boolean solucion
) {
    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getAutor(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }
}