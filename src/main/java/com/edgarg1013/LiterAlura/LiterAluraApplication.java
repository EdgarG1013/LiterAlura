package com.edgarg1013.LiterAlura;

import com.edgarg1013.LiterAlura.Principal.Principal;
import com.edgarg1013.LiterAlura.Repository.gutendexRepository;
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
    private gutendexRepository repositorio;

    @Override
    public void run(String... args) throws Exception {

        // llamamos a la funcion principal con el menu y los metodos
        Principal principal = new Principal(repositorio);
        principal.muestraElMenu();
    }
}
