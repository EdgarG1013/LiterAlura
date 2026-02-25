package com.edgarg1013.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordRespuesta(

        @JsonAlias("results") List<RecordLibro> resultados ) {
}
