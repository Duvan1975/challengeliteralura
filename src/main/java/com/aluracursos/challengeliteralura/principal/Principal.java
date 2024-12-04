package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.modelo.Datos;
import com.aluracursos.challengeliteralura.modelo.DatosAutorEntity;
import com.aluracursos.challengeliteralura.modelo.DatosLibros;
import com.aluracursos.challengeliteralura.modelo.DatosLibrosEntity;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;
import com.aluracursos.challengeliteralura.service.DatosAutorService;
import com.aluracursos.challengeliteralura.service.DatosLibrosService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final DatosLibrosService datosLibrosService;
    private final DatosAutorService datosAutorService;

    public Principal(DatosLibrosService datosLibrosService, DatosAutorService datosAutorService) {
        this.datosLibrosService = datosLibrosService;
        this.datosAutorService = datosAutorService;
    }

    public void muestraMenu() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar y guardar libro");
            System.out.println("2. Visualizar libros guardados");
            System.out.println("3. Listar libros por autor");
            System.out.println("4. Listar autores vivos en un año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");
            String opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    buscarYGuardarLibro();
                    break;
                case "2":
                    visualizarLibrosGuardados();
                    break;
                case "3":
                    listarLibrosPorAutor();
                    break;
                case "4":
                    listarAutoresVivosEnAnio();
                    break;
                case "5":
                    listarLibrosPorIdioma();
                    break;
                case "6":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
    private void buscarYGuardarLibro() {
        System.out.println("Por favor, digite el título del libro:");
        var tituloLibro = teclado.nextLine();

        if (datosLibrosService.existeLibro(tituloLibro)) {
            System.out.println("El libro ya existe en la base de datos.");
            return;
        }

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().equalsIgnoreCase(tituloLibro))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibros libro = libroBuscado.get();
            System.out.println("Libro encontrado: ");
            System.out.println("Título: " + libro.titulo());
            libro.autor().forEach(autor -> {
                System.out.println("    Nombre: " + autor.nombre());
                System.out.println("    Año de Nacimiento: " + autor.fechaDeNacimiento());
                System.out.println("    Año de Fallecimiento: " + autor.fechaDeFallecimiento());
            });

            System.out.println("Idiomas: " + libro.idiomas());
            System.out.println("Número de Descargas: " + libro.numeroDescargas());

            // Guardar en base de datos
            datosLibrosService.guardarLibro(libro);
            System.out.println("Libro guardado en la base de datos.");
        } else {
            System.out.println("Libro NO encontrado, o ya se encuentra en la base de datos");
        }
    }

    private void visualizarLibrosGuardados() {
        System.out.println("Libros almacenados en la base de datos:");
        var libros = datosLibrosService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros almacenados.");
        } else {
            libros.forEach(libro -> {
                System.out.println("ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de Descargas: " + libro.getNumeroDescargas());
                libro.getAutor().forEach(autor -> {
                    System.out.println("  Autor: " + autor.getNombre());
                    System.out.println("  Año de Nacimiento: " + autor.getFechaDeNacimiento());
                    System.out.println("  Año de Fallecimiento: " + autor.getFechaDeFallecimiento());
                });
                System.out.println("-------------------------------------------------");
            });
        }
    }
    private void listarLibrosPorAutor() {
        System.out.println("Por favor, ingrese el nombre del autor:");
        String nombreAutor = teclado.nextLine();
        List<DatosLibrosEntity> libros = datosLibrosService.obtenerLibrosPorAutor(nombreAutor);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + nombreAutor);
        } else {
            System.out.println("Libros encontrados:");
            libros.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de Descargas: " + libro.getNumeroDescargas());
                libro.getAutor().forEach(autor -> {
                    System.out.println("    Autor: " + autor.getNombre());
                    System.out.println("    Año de Nacimiento: " + autor.getFechaDeNacimiento());
                    System.out.println("    Año de Fallecimiento: " + autor.getFechaDeFallecimiento());
                });
                System.out.println("-------------------------------------------------");
            });
        }
    }
    private void listarAutoresVivosEnAnio() {
        System.out.println("Por favor, ingrese el año:");
        String anio = teclado.nextLine();

        List<DatosAutorEntity> autores = datosAutorService.obtenerAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Año de Nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println("Año de Fallecimiento: " + (autor.getFechaDeFallecimiento() == null ? "Aún vivo" : autor.getFechaDeFallecimiento()));
                System.out.println("-------------------------------------------------");
            });
        }
    }
    private void listarLibrosPorIdioma() {
        System.out.println("Seleccione el idioma:");
        System.out.println("es - Español");
        System.out.println("en - Inglés");
        System.out.println("fr - Francés");
        System.out.println("pt - Portugués");

        String idioma = teclado.nextLine();

        if (!List.of("es", "en", "fr", "pt").contains(idioma)) {
            System.out.println("Idioma no válido. Intente de nuevo.");
            return;
        }

        List<DatosLibrosEntity> libros = datosLibrosService.obtenerLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma seleccionado (" + idioma + ").");
        } else {
            System.out.println("Libros encontrados en idioma: " + idioma);
            libros.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de Descargas: " + libro.getNumeroDescargas());
                System.out.println("-------------------------------------------------");
            });
        }
    }

}
