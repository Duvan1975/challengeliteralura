package com.aluracursos.challengeliteralura.service;

import com.aluracursos.challengeliteralura.modelo.DatosAutorEntity;
import com.aluracursos.challengeliteralura.modelo.DatosLibros;
import com.aluracursos.challengeliteralura.modelo.DatosLibrosEntity;
import com.aluracursos.challengeliteralura.repository.DatosLibrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatosLibrosService {
    private final DatosLibrosRepository repository;
    public DatosLibrosService(DatosLibrosRepository repository){
        this.repository = repository;
    }
    public boolean existeLibro(String titulo) {
        return repository.findByTituloIgnoreCase(titulo).isPresent();
    }
    public DatosLibrosEntity guardarLibro(DatosLibros datosLibros){
        DatosLibrosEntity entidad = new DatosLibrosEntity();
        entidad.setTitulo(datosLibros.titulo());
        entidad.setIdiomas(datosLibros.idiomas());
        entidad.setNumeroDescargas(datosLibros.numeroDescargas());
        entidad.setAutor(datosLibros.autor().stream().map(autor->{
            DatosAutorEntity autorEntity = new DatosAutorEntity();
            autorEntity.setNombre(autor.nombre());
            autorEntity.setFechaDeNacimiento(autor.fechaDeNacimiento());
            autorEntity.setFechaDeFallecimiento(autor.fechaDeFallecimiento());
            return autorEntity;
        }).collect(Collectors.toList()));
        return repository.save(entidad);
    }
    public List<DatosLibrosEntity> obtenerTodosLosLibros() {
        return repository.findAll();
    }
    public List<DatosLibrosEntity> obtenerLibrosPorAutor(String nombreAutor) {
        return repository.findByAutorNombre(nombreAutor);
    }
    public List<DatosAutorEntity> obtenerAutoresVivosEnAnio(String anio) {
        return repository.findAutoresVivosEnAnio(anio);
    }
    public List<DatosLibrosEntity> obtenerLibrosPorIdioma(String idioma) {
        return repository.findByIdioma(idioma);
    }

}
