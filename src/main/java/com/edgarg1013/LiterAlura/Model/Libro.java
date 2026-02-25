package com.edgarg1013.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numeroDescargas;

    @Column(length = 2000)
    private String resumen;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    public Libro() {
    }

    public Libro(RecordLibro datos) {
        this.titulo = datos.titulo();
        this.idioma = datos.idiomas() != null && !datos.idiomas().isEmpty()
                ? datos.idiomas().get(0) : "Desconocido";
        this.numeroDescargas = datos.numeroDescargas();
        this.resumen = datos.resumenes() != null && !datos.resumenes().isEmpty()
                ? datos.resumenes().get(0) : "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        String nombresAutores = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));
        return "\n---------- LIBRO ----------" +
                "\nTítulo: " + titulo +
                "\nAutor(es): " + nombresAutores +
                "\nIdioma: " + idioma +
                "\nNúmero de descargas: " + numeroDescargas +
                "\n----------------------------\n";
    }
}
