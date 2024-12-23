package com.example.GestionEmergencias.Ambulancias.Service;

import com.example.GestionEmergencias.Ambulancias.Entity.Ambulancia;
import com.example.GestionEmergencias.Ambulancias.Entity.Accidentado;
import com.example.GestionEmergencias.Ambulancias.Entity.EmergenciaResueltaAmbulancia;
import com.example.GestionEmergencias.Ambulancias.Entity.Hospital;
import com.example.GestionEmergencias.Ambulancias.Repository.AmbulanciaRepository;
import com.example.GestionEmergencias.Ambulancias.Repository.AccidentadoRepository;
import com.example.GestionEmergencias.Ambulancias.Repository.EmergenciaResueltaAmbulanciaRepository;
import com.example.GestionEmergencias.Ambulancias.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmergenciaResueltaAmbulanciaService {

    @Autowired
    private AmbulanciaRepository ambulanciaRepository;

    @Autowired
    private AccidentadoRepository accidentadoRepository;

    @Autowired
    private EmergenciaResueltaAmbulanciaRepository emergenciaResueltaAmbulanciaRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public Mono<Void> resolverEmergencia(Ambulancia ambulancia, Accidentado accidentado) {
        return Mono.delay(Duration.ofSeconds(15))
                .doOnNext(ignore -> {
                    EmergenciaResueltaAmbulancia resuelta = new EmergenciaResueltaAmbulancia();
                    resuelta.setFechaResolucion(LocalDateTime.now());
                    resuelta.setNombreConductor(ambulancia.getNombreConductor());
                    resuelta.setNombreAccidentado(accidentado.getNombre() + " " + accidentado.getApellido());
                    resuelta.setCiudad(ambulancia.getCiudad());
                    resuelta.setPais(ambulancia.getPais());
                    resuelta.setTipoAccidente(accidentado.getTipoAccidente());
                    emergenciaResueltaAmbulanciaRepository.save(resuelta);

                    Hospital hospital = new Hospital();
                    hospital.setNombreHospital(ambulancia.getHospital());
                    hospital.setNombreIngresado(accidentado.getNombre() + " " + accidentado.getApellido());
                    hospital.setCiudad(ambulancia.getCiudad());
                    hospital.setPais(ambulancia.getPais());
                    hospitalRepository.save(hospital);

                    ambulanciaRepository.deleteById(ambulancia.getId());
                    accidentadoRepository.deleteById(accidentado.getId());
                })
                .then();
    }

    public List<EmergenciaResueltaAmbulancia> findAll() {
        return emergenciaResueltaAmbulanciaRepository.findAll();
    }
}