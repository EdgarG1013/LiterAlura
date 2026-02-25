package com.edgarg1013.LiterAlura.Repository;

import com.edgarg1013.LiterAlura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloIgnoreCase(String titulo);
}
