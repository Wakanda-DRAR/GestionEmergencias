package com.example.GestionEmergencias.Policia.Service;

import com.example.GestionEmergencias.Policia.Entity.Delito;
import com.example.GestionEmergencias.Policia.Repository.DelitoRepository;
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
public class DelitoService {

    @Autowired
    private DelitoRepository delitoRepository;

    private final List<String> nombresDelincuentes = List.of("Erik", "N'Jadaka", "Klaue", "Killmonger", "Zemo", "T'Chaka", "N'Jobu", "W'Kabi", "Ayo", "Nakia");
    private final List<String> tiposDelitos = List.of("Robo", "Asesinato", "Fraude", "Secuestro", "Contrabando", "Extorsión", "Vandalismo", "Tráfico de drogas");
    private final List<String> calles = List.of("Calle de la Pantera", "Calle del Vibranium", "Calle de los Ancestros", "Calle de la Tribu Dorada", "Calle de la Tribu Fronteriza");
    private final Random random = new Random();

    public Flux<Delito> generarDelitos() {
        return Flux.interval(Duration.ofSeconds(25))
                .flatMap(i -> Mono.fromCallable(() -> {
                    Delito delito = new Delito();
                    delito.setNombreDelincuente(nombresDelincuentes.get(random.nextInt(nombresDelincuentes.size())));
                    delito.setTipoDelito(tiposDelitos.get(random.nextInt(tiposDelitos.size())));
                    delito.setFecha(LocalDate.now().toString());
                    delito.setHora(LocalTime.now().toString());
                    delito.setCiudad("Wakanda");
                    delito.setPais("Wakanda");
                    delito.setCalle(calles.get(random.nextInt(calles.size())));
                    return delitoRepository.save(delito);
                }));
    }
}