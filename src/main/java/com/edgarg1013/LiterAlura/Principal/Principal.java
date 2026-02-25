package com.edgarg1013.LiterAlura.Principal;

import com.edgarg1013.LiterAlura.Model.*;
import com.edgarg1013.LiterAlura.service.ConsumoAPI;
import com.edgarg1013.LiterAlura.service.ConvierteDatos;
import com.edgarg1013.LiterAlura.Repository.AutorRepository;
import com.edgarg1013.LiterAlura.Repository.LibroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {

        int opcion;

        var menu = """
                    ------------------------------------------------
                                Bienvenido a LiterAlura 
                    ------------------------------------------------
                    Eliga una de las opciones para continuar
                    ------------------------------------------------
                    1 - Buscar Libro por titulo
                    2 - Listar Libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar Libros por idioma
                    0 - Salir
                    ------------------------------------------------
                   """;
        do {

            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine(); // consumir el salto de línea pendiente

            switch (opcion) {

                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    listarLibrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresEntreAnio();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("------------------------------------------------");
                    System.out.println("             Saliendo del programa              ");
                    System.out.println("------------------------------------------------");
                    break;

                default:
                    System.out.println("Opción no válida, intente de nuevo.");

            }

        } while (opcion != 0);

    }

    private RecordLibro obtenerEnlaApi() {

        System.out.println("------------------------------------------------");
        System.out.println(" Ingrese el titulo del libro que desea buscar");
        System.out.println("------------------------------------------------");

        var nombreLibro = entrada.nextLine();

        // guardamos los datos de la petición a la api en la variable "json"
        var json = consumoApi.obtenerDatos(URL_BASE + "search=" + nombreLibro.replace(" ", "%20"));

        // convertimos los datos de la peticion a nuestra clase wrapper RecordRespuesta
        RecordRespuesta respuesta = conversor.obtenerDatos(json, RecordRespuesta.class);

        // tomamos el primer resultado de la lista
        if (respuesta.resultados() != null && !respuesta.resultados().isEmpty()) {
            return respuesta.resultados().get(0);
        }

        return null;
    }

    private void buscarLibroPorTitulo() {

        RecordLibro datos = obtenerEnlaApi();

        if (datos == null) {
            System.out.println("------------------------------------------------");
            System.out.println(" Libro no encontrado en la API.");
            return;
        }

        // Verificar si el libro ya existe en la BD
        Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(datos.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("------------------------------------------------");
            System.out.println(" El libro ya está registrado en la base de datos:");
            System.out.println(libroExistente.get());
            return;
        }

        // Crear el libro a partir de los datos de la API
        Libro libro = new Libro(datos);

        // Procesar autores - buscar existentes o crear nuevos
        List<Autor> autoresDelLibro = new ArrayList<>();
        if (datos.autores() != null) {
            for (RecordAutor recordAutor : datos.autores()) {
                // Buscar si el autor ya existe en la BD
                Optional<Autor> autorExistente = autorRepository.findByNombre(recordAutor.nombre());
                if (autorExistente.isPresent()) {
                    autoresDelLibro.add(autorExistente.get());
                } else {
                    Autor nuevoAutor = new Autor(recordAutor);
                    autoresDelLibro.add(nuevoAutor);
                }
            }
        }

        libro.setAutores(autoresDelLibro);
        libroRepository.save(libro);

        System.out.println("------------------------------------------------");
        System.out.println("Libro guardado exitosamente:");
        System.out.println(libro);

    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("------------------------------------------------");
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("------------------------------------------------");
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresEntreAnio() {
    }

    private void listarLibrosPorIdioma() {
    }

}
