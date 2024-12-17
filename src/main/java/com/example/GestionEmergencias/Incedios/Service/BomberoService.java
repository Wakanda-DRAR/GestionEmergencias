package com.example.GestionEmergencias.Incedios.Service;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Repository.BomberoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BomberoService {

    @Autowired
    private BomberoRepository bomberoRepository;

    private final List<String> nombres = List.of("T'Challa", "Shuri", "Nakia", "Okoye", "M'Baku", "Zuri", "Ramonda", "N'Jobu", "W'Kabi", "Ayo");
    private final List<String> apellidos = List.of("Udaku", "Kazi", "N'Gabo", "N'Yami", "N'Jadaka", "N'Kano", "N'Dare", "N'Gassi", "N'Baku", "N'Jobu");
    private final Random random = new Random();

    public Flux<Bombero> asignarBomberos(Flux<Incendio> incendios) {
        return incendios.delayElements(Duration.ofSeconds(15)) // 10 segundos después de generarse el incendio
                .flatMap(incendio -> Mono.fromCallable(() -> {
                    Bombero bombero = crearBombero(incendio);
                    return bomberoRepository.save(bombero);
                }));
    }

    private Bombero crearBombero(Incendio incendio) {
        Random random = new Random();
        Bombero bombero = new Bombero();
        bombero.setNombreDepartamento("Bomberos " + incendio.getCiudad());
        bombero.setNombreBombero(generarNombreAleatorio());
        bombero.setCodigoIdentificacion(generarCodigoIdentificacion());
        bombero.setEdad(25 + random.nextInt(20));
        bombero.setAniosServicio(1 + random.nextInt(30));
        return bombero;
    }

    private String generarNombreAleatorio() {
        String nombre = nombres.get(random.nextInt(nombres.size()));
        String apellido = apellidos.get(random.nextInt(apellidos.size()));
        return nombre + " " + apellido;
    }

    private String generarCodigoIdentificacion() {
        Random random = new Random();
        int numeroAleatorio = 1000000 + random.nextInt(9000000); // Genera un número de 7 dígitos
        return "#" + numeroAleatorio;
    }


}

