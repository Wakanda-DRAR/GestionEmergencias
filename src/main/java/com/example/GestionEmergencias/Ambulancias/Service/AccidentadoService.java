package com.example.GestionEmergencias.Ambulancias.Service;

import com.example.GestionEmergencias.Ambulancias.Entity.Accidentado;
import com.example.GestionEmergencias.Ambulancias.Repository.AccidentadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Service
public class AccidentadoService {

    @Autowired
    private AccidentadoRepository accidentadoRepository;

    private final List<String> nombres = List.of("Tony", "Steve", "Natasha", "Wanda", "Vision");
    private final List<String> apellidos = List.of("Stark", "Rogers", "Romanoff", "Maximoff", "Synthozoid");
    private final List<String> tiposAccidente = List.of("Accidente de transito", "caida", "quemado", "drogado", "envenenado");
    private final List<String> ciudades = List.of("Birnin Zana", "Cibuza", "N'Jadaka", "N'Yami", "N'Gabo", "N'Jadaka", "N'Jadaka", "N'Jadaka", "N'Jadaka");
    private final Random random = new Random();

    public Mono<Accidentado> generarAccidentado() {
        return Mono.delay(Duration.ofSeconds(15))
                .flatMap(i -> Mono.fromCallable(() -> {
                    Accidentado accidentado = new Accidentado();
                    accidentado.setNombre(nombres.get(random.nextInt(nombres.size())));
                    accidentado.setApellido(apellidos.get(random.nextInt(apellidos.size())));
                    accidentado.setAnios(random.nextInt(80) + 1);
                    accidentado.setTipoAccidente(tiposAccidente.get(random.nextInt(tiposAccidente.size())));
                    accidentado.setCiudad(ciudades.get(random.nextInt(ciudades.size())));
                    accidentado.setPais("Wakanda");
                    return accidentadoRepository.save(accidentado);
                }));
    }

    public List<Accidentado> findAll() {
        return accidentadoRepository.findAll();
    }
}