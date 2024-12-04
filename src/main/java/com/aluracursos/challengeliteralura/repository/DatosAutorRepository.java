package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.modelo.DatosAutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatosAutorRepository extends JpaRepository<DatosAutorEntity, Long> {
    @Query("SELECT DISTINCT a FROM DatosAutorEntity a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento > :anio)")
    List<DatosAutorEntity> findAutoresVivosEnAnio(@Param("anio") String anio);
}

