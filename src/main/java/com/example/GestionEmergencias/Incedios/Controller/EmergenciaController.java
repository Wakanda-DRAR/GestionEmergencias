package com.example.GestionEmergencias.Incedios.Controller;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Service.BomberoService;
import com.example.GestionEmergencias.Incedios.Service.IncendioResolucionService;
import com.example.GestionEmergencias.Incedios.Service.IncendioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/emergencias")
public class EmergenciaController {

    @Autowired
    private IncendioService incendioService;

    @Autowired
    private BomberoService bomberoService;

    @Autowired
    private IncendioResolucionService resolucionService;

    private Flux<Incendio> incendios;
    private Flux<Bombero> bomberos;

    @PostConstruct
    public void init() {
        incendios = incendioService.generarIncendios().share();
        bomberos = bomberoService.asignarBomberos(incendios).share();
    }


    @GetMapping("/iniciar")
    public Flux<String> iniciarEmergencias() {
        Flux<Void> resoluciones = Flux.zip(incendios, bomberos) // Combinar incendios y bomberos
                .doOnNext(tuple -> {
                    Incendio incendio = tuple.getT1();
                    Bombero bombero = tuple.getT2();
                    resolucionService.resolverIncendioYBombero(incendio, bombero).subscribe();
                })
                .thenMany(Flux.empty());

        // Respuesta combinada con el progreso de cada paso
        return Flux.merge(
                incendios.map(incendio -> "Incendio generado en: " + incendio.getCiudad()),
                bomberos.map(bombero -> "Bombero asignado: " + bombero.getNombreBombero()),
                resoluciones.thenMany(Flux.just("Todos los incendios han sido resueltos y los bomberos completaron su misión."))
        );
    }


}
