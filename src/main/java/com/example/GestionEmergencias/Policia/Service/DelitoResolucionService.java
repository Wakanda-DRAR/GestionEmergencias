package com.example.GestionEmergencias.Policia.Service;

import com.example.GestionEmergencias.Policia.Entity.Policia;
import com.example.GestionEmergencias.Policia.Entity.Delito;
import com.example.GestionEmergencias.Policia.Entity.EmergenciaResueltaPolicia;
import com.example.GestionEmergencias.Policia.Entity.Carcel;
import com.example.GestionEmergencias.Policia.Repository.EmergenciaResueltaPoliciaRepository;
import com.example.GestionEmergencias.Policia.Repository.PoliciaRepository;
import com.example.GestionEmergencias.Policia.Repository.DelitoRepository;
import com.example.GestionEmergencias.Policia.Repository.CarcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class DelitoResolucionService {

    @Autowired
    private DelitoRepository delitoRepository;

    @Autowired
    private PoliciaRepository policiaRepository;

    @Autowired
    private EmergenciaResueltaPoliciaRepository emergenciaResueltaPoliciaRepository;

    @Autowired
    private CarcelRepository carcelRepository;

    public Mono<Void> resolverDelitoYPolicia(Delito delito, Policia policia) {
        return Mono.delay(Duration.ofSeconds(20))
                .doOnNext(ignore -> {
                    EmergenciaResueltaPolicia resuelta = new EmergenciaResueltaPolicia();
                    resuelta.setDelitoId(delito.getId());
                    resuelta.setCiudad(delito.getCiudad());
                    resuelta.setPais(delito.getPais());
                    resuelta.setCalle(delito.getCalle());
                    resuelta.setFechaResolucion(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    resuelta.setPoliciaId(policia.getPlaca());
                    resuelta.setNombrePolicia(policia.getNombre());
                    resuelta.setNombreDepartamento(policia.getDepartamento());
                    emergenciaResueltaPoliciaRepository.save(resuelta);

                    Carcel carcel = new Carcel();
                    carcel.setNombreDelincuente(delito.getNombreDelincuente());
                    carcel.setAniosCarcel(5 + new Random().nextInt(20));
                    carcel.setNombreCarcel("Carcel Central de Wakanda");
                    carcel.setPais(delito.getPais());
                    carcel.setCiudad(delito.getCiudad());
                    carcelRepository.save(carcel);

                    delitoRepository.deleteById(delito.getId());
                    policiaRepository.deleteById(policia.getId());

                    System.out.println("Delito resuelto tras 20 segundos y delincuente enviado a la cárcel.");
                })
                .then();
    }
}