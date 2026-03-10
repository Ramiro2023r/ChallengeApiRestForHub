package com.aluracursos.forohub.domain.topico;


import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        StatusTopico status
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}