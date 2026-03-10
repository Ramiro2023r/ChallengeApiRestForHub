package com.aluracursos.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findAllByActivoTrue(Pageable pageable);

    Page<Topico> findByCursoAndActivoTrue(String curso, Pageable pageable);

    @Query("""
        SELECT t FROM Topico t
        WHERE t.curso = :curso
        AND YEAR(t.fechaCreacion) = YEAR(:fecha)
        AND t.activo = true
    """)
    Page<Topico> findByCursoAndAño(
            @Param("curso") String curso,
            @Param("fecha") LocalDate fecha,
            Pageable pageable);
}