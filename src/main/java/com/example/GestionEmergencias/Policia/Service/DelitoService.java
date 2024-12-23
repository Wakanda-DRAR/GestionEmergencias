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

    private final List<String> nombres = List.of("Loki", "Thanos", "Ultron", "Hela", "Red Skull", "Green Goblin", "Doctor Doom", "Magneto", "Venom", "Kingpin");
    private final List<String> apellidos = List.of("Laufeyson", "Titan", "AI", "Goddess of Death", "Schmidt", "Osborn", "Von Doom", "Lehnsherr", "Brock", "Fisk");
    private final List<String> tiposDelitos = List.of("Robo", "Asesinato", "Fraude", "Secuestro", "Contrabando", "Extorsión", "Vandalismo", "Tráfico de drogas");
    private final List<String> calles = List.of("Calle de la Pantera", "Calle del Vibranium", "Calle de los Ancestros", "Calle de la Tribu Dorada", "Calle de la Tribu Fronteriza");
    private final List<String> ciudades = List.of("Birnin Zana", "Cibuza", "N'Jadaka", "N'Yami", "N'Gabo", "N'Jadaka", "N'Jadaka", "N'Jadaka", "N'Jadaka");
    private final Random random = new Random();

    public Flux<Delito> generarDelitos() {
        return Flux.interval(Duration.ofSeconds(25))
                .flatMap(i -> Mono.fromCallable(() -> {
                    Delito delito = new Delito();
                    delito.setNombreDelincuente(generarNombreDelincuente());
                    delito.setTipoDelito(tiposDelitos.get(random.nextInt(tiposDelitos.size())));
                    delito.setFecha(LocalDate.now().toString());
                    delito.setHora(LocalTime.now().toString());
                    delito.setCiudad(ciudades.get(random.nextInt(ciudades.size())));
                    delito.setPais("Wakanda");
                    delito.setCalle(calles.get(random.nextInt(calles.size())));
                    return delitoRepository.save(delito);
                }));
    }

    private String generarNombreDelincuente() {
        String nombre = nombres.get(random.nextInt(nombres.size()));
        String apellido = apellidos.get(random.nextInt(apellidos.size()));
        return nombre + " " + apellido;
    }
}