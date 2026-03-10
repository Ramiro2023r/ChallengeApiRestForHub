package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.*;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas", description = "Gestión de respuestas a tópicos")
public class RespuestaController {

    @Autowired private RespuestaRepository respuestaRepository;
    @Autowired private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar respuesta a un tópico")
    public ResponseEntity<DatosRespuestaRespuesta> registrar(
            @RequestBody @Valid DatosRegistroRespuesta datos,
            UriComponentsBuilder uriBuilder) {

        var topico = topicoRepository.findById(datos.topicoId());

        if (topico.isEmpty()) {
            throw new RuntimeException("Tópico con ID " + datos.topicoId() + " no encontrado.");
        }

        Respuesta respuesta = new Respuesta(datos, topico.get());
        respuestaRepository.save(respuesta);

        URI url = uriBuilder.path("/respuestas/{id}")
                .buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(new DatosRespuestaRespuesta(respuesta));
    }

    @GetMapping("/topico/{topicoId}")
    @Operation(summary = "Listar respuestas de un tópico")
    public ResponseEntity<Page<DatosRespuestaRespuesta>> listarPorTopico(
            @PathVariable Long topicoId,
            @PageableDefault(size = 10) Pageable paginacion) {

        Page<DatosRespuestaRespuesta> page = respuestaRepository
                .findByTopicoId(topicoId, paginacion)
                .map(DatosRespuestaRespuesta::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar una respuesta por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new RuntimeException("Respuesta con ID " + id + " no encontrada.");
        }
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}