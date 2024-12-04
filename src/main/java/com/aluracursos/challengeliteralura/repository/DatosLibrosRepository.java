package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.modelo.DatosAutorEntity;
import com.aluracursos.challengeliteralura.modelo.DatosLibrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DatosLibrosRepository extends JpaRepository<DatosLibrosEntity, Long> {
    Optional<DatosLibrosEntity> findByTitulo(String titulo);

    @Query("SELECT d FROM DatosLibrosEntity d WHERE LOWER(d.titulo) = LOWER(:titulo)")
    Optional<DatosLibrosEntity> findByTituloIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT l FROM DatosLibrosEntity l JOIN l.autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAutor, '%'))")
    List<DatosLibrosEntity> findByAutorNombre(@Param("nombreAutor") String nombreAutor);

    @Query("SELECT DISTINCT a FROM DatosAutorEntity a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento > :anio)")
    List<DatosAutorEntity> findAutoresVivosEnAnio(@Param("anio") String anio);

    @Query("SELECT l FROM DatosLibrosEntity l WHERE :idioma MEMBER OF l.idiomas")
    List<DatosLibrosEntity> findByIdioma(@Param("idioma") String idioma);
}
