package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.domain.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuarios", description = "Gestión de usuarios")
public class UsuarioController {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar nuevo usuario")
    public ResponseEntity<DatosRespuestaUsuario> registrar(
            @RequestBody @Valid DatosRegistroUsuario datos,
            UriComponentsBuilder uriBuilder) {

        if (usuarioRepository.findByLogin(datos.login()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese login.");
        }

        Usuario usuario = new Usuario(
                null,
                datos.login(),
                passwordEncoder.encode(datos.clave())
        );
        usuarioRepository.save(usuario);

        URI url = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(
            @PageableDefault(size = 10) Pageable paginacion) {

        Page<DatosRespuestaUsuario> page = usuarioRepository
                .findAll(paginacion)
                .map(DatosRespuestaUsuario::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar usuario por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario con ID " + id + " no encontrado.");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}