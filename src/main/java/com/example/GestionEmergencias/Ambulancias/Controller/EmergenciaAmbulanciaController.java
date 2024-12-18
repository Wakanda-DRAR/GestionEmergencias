package com.example.GestionEmergencias.Ambulancias.Controller;

import com.example.GestionEmergencias.Ambulancias.Entity.Ambulancia;
import com.example.GestionEmergencias.Ambulancias.Entity.Accidentado;
import com.example.GestionEmergencias.Ambulancias.Entity.EmergenciaResueltaAmbulancia;
import com.example.GestionEmergencias.Ambulancias.Entity.Hospital;
import com.example.GestionEmergencias.Ambulancias.Service.AmbulanciaService;
import com.example.GestionEmergencias.Ambulancias.Service.AccidentadoService;
import com.example.GestionEmergencias.Ambulancias.Service.EmergenciaResueltaAmbulanciaService;
import com.example.GestionEmergencias.Ambulancias.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmergenciaAmbulanciaController {

    @Autowired
    private AmbulanciaService ambulanciaService;

    @Autowired
    private AccidentadoService accidentadoService;

    @Autowired
    private EmergenciaResueltaAmbulanciaService emergenciaResueltaAmbulanciaService;

    @Autowired
    private HospitalService hospitalService;

    @Bean
    public ApplicationRunner iniciarFlujosAmbulancia() {
        return args -> {
            Flux<Accidentado> accidentados = accidentadoService.generarAccidentado().repeat().share();
            Flux<Ambulancia> ambulancias = accidentados.delayElements(Duration.ofSeconds(15))
                    .flatMap(accidentado -> ambulanciaService.generarAmbulancia().map(ambulancia -> {
                        emergenciaResueltaAmbulanciaService.resolverEmergencia(ambulancia, accidentado).subscribe();
                        return ambulancia;
                    })).share();

            ambulancias.subscribe();
        };
    }

    @GetMapping("/ambulancias")
    public List<Ambulancia> getAmbulancias() {
        return ambulanciaService.findAll();
    }

    @GetMapping("/accidentados")
    public List<Accidentado> getAccidentados() {
        return accidentadoService.findAll();
    }

    @GetMapping("/resolvedAmbulancia")
    public List<EmergenciaResueltaAmbulancia> getResolvedEmergencias() {
        return emergenciaResueltaAmbulanciaService.findAll();
    }

    @GetMapping("/hospitales")
    public List<Hospital> getHospitales() {
        return hospitalService.findAll();
    }
}