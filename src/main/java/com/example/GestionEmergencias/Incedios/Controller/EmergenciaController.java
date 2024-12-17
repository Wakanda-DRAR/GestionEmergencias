package com.example.GestionEmergencias.Incedios.Controller;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Service.BomberoService;
import com.example.GestionEmergencias.Incedios.Service.IncendioService;
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

    @GetMapping("/iniciar")
    public Flux<String> iniciarEmergencias() {
        Flux<Incendio> incendios = incendioService.generarIncendios();
        Flux<Bombero> bomberos = bomberoService.asignarBomberos(incendios);

        return Flux.merge(
                incendios.map(incendio -> "Incendio generado en: " + incendio.getCiudad()),
                bomberos.map(bombero -> "Bombero asignado: " + bombero.getNombreBombero())
        );
    }
}

