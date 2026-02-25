package com.edgarg1013.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RecordAutor(

        @JsonAlias("name") String titulo,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento ) {
}
