package com.edgarg1013.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordLibro(

        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<RecordAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas,
        @JsonAlias("summaries") List<String> resumenes ) {
}
