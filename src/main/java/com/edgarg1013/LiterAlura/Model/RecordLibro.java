package com.edgarg1013.LiterAlura.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordLibro(

        @JsonAlias("Title") String titulo,
        @JsonAlias("authors")String autor,
        @JsonAlias("languages")String idioma,
        @JsonAlias("download_count")Double numeroDescargas,
        @JsonAlias("summaries")String resumen ) {
}
