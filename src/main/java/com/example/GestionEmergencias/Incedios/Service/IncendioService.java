package com.example.GestionEmergencias.Incedios.Service;

import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Repository.IncendioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class IncendioService {

    @Autowired
    private IncendioRepository incendioRepository;

    private final List<String> ciudades = List.of("Birnin Zana", "Cibuza", "N'Jadaka", "N'Yami", "N'Gabo", "N'Jadaka", "N'Jadaka", "N'Jadaka", "N'Jadaka");
    private final List<String> calles = List.of("Calle de la Pantera", "Calle del Vibranium", "Calle de los Ancestros", "Calle de la Tribu Dorada", "Calle de la Tribu Fronteriza", "Calle de la Tribu del Río", "Calle de la Tribu Minera", "Calle de la Tribu Jabari", "Calle de la Tribu Mercante", "Calle del Gran Muro", "Calle del Palacio Real", "Calle del Mercado Central", "Calle de la Sabiduría", "Calle de la Unidad", "Calle de la Libertad");
    private final Random random = new Random();

    public Flux<Incendio> generarIncendios() {
        return Flux.interval(Duration.ofSeconds(25)) // Genera un incendio cada 25 segundos
                .flatMap(i -> Mono.fromCallable(() -> {
                    Incendio incendio = new Incendio();
                    incendio.setCiudad(ciudades.get(random.nextInt(ciudades.size())));
                    incendio.setPais("Wakanda");
                    incendio.setFecha(LocalDate.now().toString());
                    incendio.setHora(LocalTime.now().toString());
                    incendio.setCalle(calles.get(random.nextInt(calles.size())));
                    return incendioRepository.save(incendio);
                }));
    }


}

