package com.edgarg1013.LiterAlura.Repository;

import com.edgarg1013.LiterAlura.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);
}
