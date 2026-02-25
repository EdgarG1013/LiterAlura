package com.edgarg1013.LiterAlura.Principal;

import com.edgarg1013.LiterAlura.Model.Libro;
import com.edgarg1013.LiterAlura.Model.RecordLibro;
import com.edgarg1013.LiterAlura.service.ConsumoAPI;
import com.edgarg1013.LiterAlura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Value;
import com.edgarg1013.LiterAlura.Repository.gutendexRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private gutendexRepository repositorio;

    public Principal(gutendexRepository repositorio) {
        this.repositorio = repositorio;
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



            }

        } while (opcion != 0);

    }

    private RecordLibro obtenerEnlaApi() {

        System.out.println("------------------------------------------------");
        System.out.println(" Ingrese el titulo del libro que desea buscar");
        System.out.println("------------------------------------------------");

        var nombreLibro = entrada.nextLine();
            entrada.nextLine();

        // guardamos los datos de la petición a la api en la variable "json"
        var json = consumoApi.obtenerDatos(URL_BASE + "search=" + nombreLibro.replace(" ", "%20"));

        // convertimos los datos de la peticion a nuestra clase de tipo record
        RecordLibro datosLibro = conversor.obtenerDatos(json, RecordLibro.class);

        return datosLibro;

    }



    private void buscarLibroPorTitulo() {

        RecordLibro datos = obtenerEnlaApi();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);

    }

    private void listarLibrosRegistrados() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresEntreAnio() {
    }

    private void listarLibrosPorIdioma() {
    }

}
