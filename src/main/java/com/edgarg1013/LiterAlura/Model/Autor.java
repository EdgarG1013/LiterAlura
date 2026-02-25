package com.edgarg1013.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(RecordAutor datos) {
        this.nombre = datos.nombre();
        this.nacimiento = datos.nacimiento();
        this.fallecimiento = datos.fallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "---- AUTOR ----" +
                "\nNombre: " + nombre +
                "\nNacimiento: " + nacimiento +
                "\nFallecimiento: " + fallecimiento +
                "\nLibros: " + libros.stream().map(l -> l.getTitulo()).collect(Collectors.toList()) +
                "\n----------------";
    }
}
