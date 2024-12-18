package com.example.GestionEmergencias.Ambulancias.Service;

import com.example.GestionEmergencias.Ambulancias.Entity.Ambulancia;
import com.example.GestionEmergencias.Ambulancias.Repository.AmbulanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Service
public class AmbulanciaService {

    @Autowired
    private AmbulanciaRepository ambulanciaRepository;

    private final List<String> nombresConductores = List.of("John", "Jane", "Peter", "Bruce", "Clark");
    private final List<String> apellidosConductores = List.of("Doe", "Smith", "Parker", "Wayne", "Kent");
    private final List<String> ciudades = List.of("Birnin Zana", "Cibuza", "N'Jadaka", "N'Yami", "N'Gabo", "N'Jadaka", "N'Jadaka", "N'Jadaka", "N'Jadaka");

    private final Random random = new Random();

    public Mono<Ambulancia> generarAmbulancia() {
        return Mono.delay(Duration.ofSeconds(15))
                .flatMap(i -> Mono.fromCallable(() -> {
                    Ambulancia ambulancia = new Ambulancia();
                    ambulancia.setIdentificadorAmbulancia("AMB-" + random.nextInt(1000));
                    ambulancia.setNombreConductor(generarNombreConductor());
                    ambulancia.setHospital("Hospital de Wakanda");
                    ambulancia.setAnios(random.nextInt(10) + 1);
                    ambulancia.setAniosServicio(random.nextInt(20) + 1);
                    ambulancia.setCiudad(ciudades.get(random.nextInt(ciudades.size())));
                    ambulancia.setPais("Wakanda");
                    return ambulanciaRepository.save(ambulancia);
                }));
    }

    public List<Ambulancia> findAll() {
        return ambulanciaRepository.findAll();
    }

    private String generarNombreConductor() {
        String nombre = nombresConductores.get(random.nextInt(nombresConductores.size()));
        String apellido = apellidosConductores.get(random.nextInt(apellidosConductores.size()));
        return nombre + " " + apellido;
    }
}