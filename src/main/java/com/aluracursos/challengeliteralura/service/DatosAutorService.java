package com.aluracursos.challengeliteralura.service;

import com.aluracursos.challengeliteralura.modelo.DatosAutorEntity;
import com.aluracursos.challengeliteralura.repository.DatosAutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosAutorService {
    private final DatosAutorRepository autorRepository;

    public DatosAutorService(DatosAutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<DatosAutorEntity> obtenerAutoresVivosEnAnio(String anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }
}

