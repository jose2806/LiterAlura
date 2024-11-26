# Proyecto LiterAlura

## Descripción

Este proyecto es una aplicación basada en Java y Spring Boot para gestionar libros y autores. 
La aplicación consume datos de la API de Gutendex y permite buscar libros por título, listar libros y autores registrados, y filtrar autores vivos en un año determinado o libros por idioma.

## Prerrequisitos

Asegúrate de tener instalados los siguientes elementos:

- Java 11 o superior
- Maven 3.6.0 o superior
- PostgreSQL (o la base de datos de tu elección)

## Configuración de Variables de Entorno

Este proyecto requiere ciertas variables de entorno para su configuración. 
Debes crear un archivo `.env` en el directorio raíz del proyecto con el siguiente contenido en la carpera resources y en el archivo application.properties:
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

Asegúrate de reemplazar `DB_USER` y `DB_PASSWORD` con tus credenciales de base de datos reales.

## Instalación

1. Clona este repositorio:
    ```sh
    git clone https://github.com/tu-usuario/literalura.git
    cd literalura
    ```

## Uso

1. Al iniciar la aplicación, se presentará un menú en la consola con las siguientes opciones:
    ```
    1 - Buscar libro por titulo
    2 - Listar libros registrados
    3 - Listar autores registrados
    4 - Listar autores vivos en un determinado año
    5 - Listar libros por idioma
    0 - Salir
    ```

2. Selecciona la opción deseada ingresando el número correspondiente.

## Desarrollo

### Estructura del Proyecto

- `src/main/java/com/alura/literAlura/` - Código fuente principal.
- `src/main/resources/` - Archivos de configuración y recursos.

### Principales Clases y Servicios

- `Main` - Clase principal que gestiona la entrada del usuario y las operaciones principales.
- `Book` y `Author` - Entidades que representan libros y autores.
- `BookRepository` y `AuthorRepository` - Repositorios de Spring Data JPA para interactuar con la base de datos.
- `ConsumeAPI` y `ConvertData` - Servicios para consumir la API externa y convertir los datos.
---



