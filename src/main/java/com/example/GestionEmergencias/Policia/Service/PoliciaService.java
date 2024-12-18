package com.example.GestionEmergencias.Policia.Service;

import com.example.GestionEmergencias.Policia.Entity.Policia;
import com.example.GestionEmergencias.Policia.Entity.Delito;
import com.example.GestionEmergencias.Policia.Repository.PoliciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Service
public class PoliciaService {

    @Autowired
    private PoliciaRepository policiaRepository;

    private final List<String> nombres = List.of("T'Challa", "Shuri", "Nakia", "Okoye", "M'Baku", "Zuri", "Ramonda", "N'Jobu", "W'Kabi", "Ayo");
    private final List<String> apellidos = List.of("Udaku", "Kazi", "N'Gabo", "N'Yami", "N'Jadaka", "N'Kano", "N'Dare", "N'Gassi", "N'Baku", "N'Jobu");
    private final Random random = new Random();

    public Flux<Policia> asignarPolicias(Flux<Delito> delitos) {
        return delitos.delayElements(Duration.ofSeconds(15))
                .flatMap(delito -> Mono.fromCallable(() -> {
                    Policia policia = crearPolicia(delito);
                    return policiaRepository.save(policia);
                }));
    }

    private Policia crearPolicia(Delito delito) {
        Policia policia = new Policia();
        policia.setNombre(generarNombreAleatorio());
        policia.setAniosServicio(1 + random.nextInt(30));
        policia.setPlaca("P" + (1000 + random.nextInt(9000)));
        policia.setEdad(25 + random.nextInt(20));
        policia.setCiudad(delito.getCiudad());
        policia.setDepartamento("Policía " + delito.getCiudad());
        policia.setPais(delito.getPais());
        return policia;
    }

    private String generarNombreAleatorio() {
        String nombre = nombres.get(random.nextInt(nombres.size()));
        String apellido = apellidos.get(random.nextInt(apellidos.size()));
        return nombre + " " + apellido;
    }
}