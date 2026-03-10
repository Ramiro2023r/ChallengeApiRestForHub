package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos", description = "CRUD completo de tópicos del foro")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // ✅ POST /topicos
    @PostMapping
    @Transactional
    @Operation(summary = "Registrar nuevo tópico")
    public ResponseEntity<DatosRespuestaTopico> registrar(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
        }

        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        URI url = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
    }

    // ✅ GET /topicos (con filtros opcionales por curso y año)
    @GetMapping
    @Operation(summary = "Listar tópicos con paginación y filtros opcionales")
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion",
                    direction = Sort.Direction.ASC) Pageable paginacion,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate año) {

        Page<Topico> page;

        if (curso != null && año != null) {
            page = topicoRepository.findByCursoAndAño(curso, año, paginacion);
        } else if (curso != null) {
            page = topicoRepository.findByCursoAndActivoTrue(curso, paginacion);
        } else {
            page = topicoRepository.findAllByActivoTrue(paginacion);
        }

        return ResponseEntity.ok(page.map(DatosListadoTopico::new));
    }

    // ✅ GET /topicos/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de un tópico por ID")
    public ResponseEntity<DatosRespuestaTopico> detallar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            throw new RuntimeException("Tópico con ID " + id + " no encontrado.");
        }

        return ResponseEntity.ok(new DatosRespuestaTopico(topico.get()));
    }

    // ✅ PUT /topicos/{id}
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar datos de un tópico")
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            throw new RuntimeException("Tópico con ID " + id + " no encontrado.");
        }

        // Validar duplicados si cambia titulo o mensaje
        if (datos.titulo() != null && datos.mensaje() != null) {
            if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
                throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
            }
        }

        Topico topico = topicoOptional.get();
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    // ✅ DELETE /topicos/{id}
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar tópico (baja lógica)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            throw new RuntimeException("Tópico con ID " + id + " no encontrado.");
        }

        topicoOptional.get().desactivar();
        return ResponseEntity.noContent().build();
    }
}