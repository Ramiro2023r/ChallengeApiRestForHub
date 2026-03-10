package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.infra.security.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;



    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticar(
            @RequestBody @Valid DatosAutenticacionUsuario datos) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.login(), datos.clave());

        Authentication usuarioAutenticado = authManager.authenticate(authToken);
        String jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

    public record DatosJWTToken(String jwTtoken) {}
}