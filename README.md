# Challenge Literalura 📚

Este es un proyecto desarrollado en Java con Spring Boot que consume datos de la API [GutenDex](https://gutendex.com/), los procesa y almacena en una base de datos relacional. Además, permite a los usuarios realizar consultas avanzadas sobre los datos almacenados.

## Funcionalidades principales 🚀

- **Buscar y guardar libros**: Permite buscar libros por título en la API y almacenarlos en la base de datos.
- **Visualizar libros guardados**: Lista todos los libros almacenados en la base de datos.
- **Listar libros por autor**: Permite buscar libros según el autor.
- **Listar autores vivos en un año específico**: Muestra los autores que estaban vivos en el año ingresado.
- **Listar libros por idioma**: Permite filtrar los libros almacenados según el idioma (`es`, `en`, `fr`, `pt`).

## Tecnologías utilizadas 🛠️

- **Java 17**: Lenguaje principal del proyecto.
- **Spring Boot**: Framework para la creación de aplicaciones empresariales.
  - Spring Data JPA: Gestión de la persistencia.
  - Spring Boot Starter Web: Desarrollo de servicios REST.
- **PostgreSQL**: Base de datos utilizada para almacenar la información.
- **Hibernate**: ORM para la interacción con la base de datos.
- **Jackson**: Para el manejo de datos JSON.
- **Maven**: Gestión de dependencias.

## Instalación y configuración ⚙️

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/challenge-literalura.git
## Uso 🖥️
Al iniciar la aplicación, se mostrará un menú interactivo en la consola.
Sigue las instrucciones para realizar las diferentes operaciones disponibles:
- Buscar libros y almacenarlos.
- Visualizar libros guardados.
- Filtrar libros por autor o idioma.
- Listar autores vivos en un año específico.
## Capturas de pantalla 📸
Menú principal:
Seleccione una opción:
1. Buscar y guardar libro
2. Visualizar libros guardados
3. Listar libros por autor
4. Listar autores vivos en un año
5. Listar libros por idioma
6. Salir

## API utilizada 🌐
GutenDex API: Fuente de datos para obtener información sobre libros. Documentación oficial.
## Estructura del proyecto 🗂️

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── aluracursos
│   │           └── challengeliteralura
│   │               ├── modelo
│   │               │   ├── Datos.java
│   │               │   ├── DatosAutor.java
│   │               │   ├── DatosAutorEntity.java
│   │               │   ├── DatosLibros.java
│   │               │   ├── DatosLibrosEntity.java
│   │               ├── principal
│   │               │   └── Principal.java
│   │               ├── repository
│   │               │   └── DatosLibrosRepository.java
│   │               ├── service
│   │                   ├── ConsumoAPI.java
│   │                   ├── ConvierteDatos.java
│   │                   ├── DatosAutorService.java
│   │                   ├── DatosLibrosService.java
│   ├── resources
│       ├── application.properties
├── test
│   └── java
│       └── com
│           └── aluracursos
│               └── challengeliteralura
│                   └── ChallengeliteraluraApplicationTests.java
```
## Contribución 🤝
¡Las contribuciones son bienvenidas! Por favor, sigue estos pasos:

Haz un fork del proyecto.
Crea una rama para tu nueva funcionalidad:
- git checkout -b nueva-funcionalidad
## Licencia 📄
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

## Autor ✍️
Duván Ballesteros Gallego
Estudiante y desarrollador en formación.
