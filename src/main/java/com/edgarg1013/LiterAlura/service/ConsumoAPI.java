package com.edgarg1013.LiterAlura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public String obtenerDatos(String url){

   // 1. Configuramos el cliente para seguir redirecciones
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        // 2. Agregamos el User-Agent a la petición
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "LiterAluraApp/1.0")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificación de seguridad
            if (response.statusCode() != 200) {
                System.out.println("Error en la API: Código " + response.statusCode());
                return "";
            }

            var json = response.body();

            return json;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al conectar con la API: " + e.getMessage());
        }
    }
}
