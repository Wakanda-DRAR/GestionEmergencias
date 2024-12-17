package com.example.GestionEmergencias.Incedios.Controller;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.EmergenciaResuelta;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Repository.BomberoRepository;
import com.example.GestionEmergencias.Incedios.Repository.EmergenciaResueltaRepository;
import com.example.GestionEmergencias.Incedios.Repository.IncendioRepository;
import com.example.GestionEmergencias.Incedios.Service.BomberoService;
import com.example.GestionEmergencias.Incedios.Service.IncendioResolucionService;
import com.example.GestionEmergencias.Incedios.Service.IncendioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmergenciaController {

    @Autowired
    private IncendioService incendioService;

    @Autowired
    private BomberoService bomberoService;

    @Autowired
    private IncendioResolucionService resolucionService;

    @Autowired
    private IncendioRepository incendioRepository;

    @Autowired
    private BomberoRepository bomberoRepository;

    @Autowired
    private EmergenciaResueltaRepository emergenciaResueltaRepository;



    @Bean
    public ApplicationRunner iniciarFlujos() {
        return args -> {
            Flux<Incendio> incendios = incendioService.generarIncendios().share();
            Flux<Bombero> bomberos = bomberoService.asignarBomberos(incendios).share();

            Flux.zip(incendios, bomberos)
                    .doOnNext(tuple -> {
                        Incendio incendio = tuple.getT1();
                        Bombero bombero = tuple.getT2();
                        resolucionService.resolverIncendioYBombero(incendio, bombero).subscribe();
                    })
                    .subscribe();
        };
    }

    @GetMapping("/incendios")
    public List<Incendio> getIncendios() {
        return incendioRepository.findAll();
    }

    @GetMapping("/bomberos")
    public List<Bombero> getBomberos() {
        return bomberoRepository.findAll();
    }


    @GetMapping("/resolved")
    public List<EmergenciaResuelta> getResolvedEmergencias() {
        return emergenciaResueltaRepository.findAll();
    }


}
