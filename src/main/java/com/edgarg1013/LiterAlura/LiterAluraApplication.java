package com.edgarg1013.LiterAlura;

import com.edgarg1013.LiterAlura.Principal.Principal;
import com.edgarg1013.LiterAlura.Repository.AutorRepository;
import com.edgarg1013.LiterAlura.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);

	}

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {

        // llamamos a la funcion principal con el menu y los metodos
        Principal principal = new Principal(libroRepository, autorRepository);
        principal.muestraElMenu();
    }
}
