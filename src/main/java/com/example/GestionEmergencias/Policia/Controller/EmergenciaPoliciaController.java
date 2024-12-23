package com.example.GestionEmergencias.Policia.Controller;

import com.example.GestionEmergencias.Policia.Entity.Carcel;
import com.example.GestionEmergencias.Policia.Entity.Delito;
import com.example.GestionEmergencias.Policia.Entity.Policia;
import com.example.GestionEmergencias.Policia.Entity.EmergenciaResueltaPolicia;
import com.example.GestionEmergencias.Policia.Repository.CarcelRepository;
import com.example.GestionEmergencias.Policia.Service.DelitoService;
import com.example.GestionEmergencias.Policia.Service.PoliciaService;
import com.example.GestionEmergencias.Policia.Service.DelitoResolucionService;
import com.example.GestionEmergencias.Policia.Repository.DelitoRepository;
import com.example.GestionEmergencias.Policia.Repository.PoliciaRepository;
import com.example.GestionEmergencias.Policia.Repository.EmergenciaResueltaPoliciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmergenciaPoliciaController {

    @Autowired
    private DelitoRepository delitoRepository;

    @Autowired
    private PoliciaRepository policiaRepository;

    @Autowired
    private EmergenciaResueltaPoliciaRepository emergenciaResueltaPoliciaRepository;

    @Autowired
    private CarcelRepository carcelRepository;

    @Autowired
    private DelitoService delitoService;

    @Autowired
    private PoliciaService policiaService;

    @Autowired
    private DelitoResolucionService delitoResolucionService;

    @Bean
    public ApplicationRunner iniciarFlujosPolicia() {
        return args -> {
            Flux<Delito> delitos = delitoService.generarDelitos().share();
            Flux<Policia> policias = policiaService.asignarPolicias(delitos).share();

            Flux.zip(delitos, policias)
                    .doOnNext(tuple -> {
                        Delito delito = tuple.getT1();
                        Policia policia = tuple.getT2();
                        delitoResolucionService.resolverDelitoYPolicia(delito, policia).subscribe();
                    })
                    .subscribe();
        };
    }

    @GetMapping("/delitos")
    public List<Delito> getDelitos() {
        return delitoRepository.findAll();
    }

    @GetMapping("/policias")
    public List<Policia> getPolicias() {
        return policiaRepository.findAll();
    }

    @GetMapping("/resolvedPolicia")
    public List<EmergenciaResueltaPolicia> getResolvedEmergencias() {
        return emergenciaResueltaPoliciaRepository.findAll();
    }

    @GetMapping("/carcel")
    public List<Carcel> getCarcel() {
        return carcelRepository.findAll();
    }

}