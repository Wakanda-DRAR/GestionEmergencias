package com.example.GestionEmergencias.Incedios.Service;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Repository.BomberoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@Service
public class BomberoService {

    @Autowired
    private BomberoRepository bomberoRepository;

    public Flux<Bombero> asignarBomberos(Flux<Incendio> incendios) {
        return incendios.delayElements(Duration.ofSeconds(10)) // 10 segundos después de generarse el incendio
                .flatMap(incendio -> Mono.fromCallable(() -> {
                    Bombero bombero = crearBombero(incendio);
                    return bomberoRepository.save(bombero);
                }));
    }

    private Bombero crearBombero(Incendio incendio) {
        Random random = new Random();
        Bombero bombero = new Bombero();
        bombero.setNombreDepartamento("Bomberos " + incendio.getCiudad());
        bombero.setNombreBombero("Bombero #" + (random.nextInt(100) + 1));
        bombero.setCodigoIdentificacion(UUID.randomUUID().toString());
        bombero.setEdad(25 + random.nextInt(20));
        bombero.setAniosServicio(1 + random.nextInt(30));
        return bombero;
    }
}

