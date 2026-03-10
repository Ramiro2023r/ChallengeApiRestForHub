package com.aluracursos.forohub.domain.usuario;


import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(

        @NotBlank(message = "El login es obligatorio")
        String login,

        @NotBlank(message = "La clave es obligatoria")
        String clave
) {}