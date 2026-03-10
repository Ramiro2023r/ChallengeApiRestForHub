package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull(message = "El ID del tópico es obligatorio")
        Long topicoId,

        @NotBlank(message = "El autor es obligatorio")
        String autor
) {}