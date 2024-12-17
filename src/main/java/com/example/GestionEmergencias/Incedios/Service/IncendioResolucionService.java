package com.example.GestionEmergencias.Incedios.Service;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.EmergenciaResuelta;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Repository.BomberoRepository;
import com.example.GestionEmergencias.Incedios.Repository.EmergenciaResueltaRepository;
import com.example.GestionEmergencias.Incedios.Repository.IncendioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class IncendioResolucionService {

    @Autowired
    private IncendioRepository incendioRepository;

    @Autowired
    private BomberoRepository bomberoRepository;

    @Autowired
    private EmergenciaResueltaRepository emergenciaResueltaRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public ApplicationRunner limpiarBasesDeDatosAlInicio() {
        return args -> {
            // Eliminar todos los registros de las bases de datos
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
            jdbcTemplate.execute("TRUNCATE TABLE incendio");
            jdbcTemplate.execute("TRUNCATE TABLE bombero");
            jdbcTemplate.execute("TRUNCATE TABLE emergencia_resuelta");
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("Bases de datos limpiadas al iniciar la aplicación.");
        };
    }

    public Mono<Void> resolverIncendioYBombero(Incendio incendio, Bombero bombero) {
        return Mono.delay(Duration.ofSeconds(25))
                .doOnNext(ignore -> {
                    EmergenciaResuelta resuelta = new EmergenciaResuelta();
                    resuelta.setIncendioId(incendio.getId());
                    resuelta.setCiudad(incendio.getCiudad());
                    resuelta.setPais(incendio.getPais());
                    resuelta.setCalle(incendio.getCalle());
                    resuelta.setFechaResolucion(LocalDate.now().toString());
                    resuelta.setBomberoId(bombero.getId());
                    resuelta.setNombreBombero(bombero.getNombreBombero());
                    resuelta.setNombreDepartamento(bombero.getNombreDepartamento());
                    emergenciaResueltaRepository.save(resuelta);

                    incendioRepository.deleteById(incendio.getId());
                    bomberoRepository.deleteById(bombero.getId());

                    System.out.println("Incendio resuelto tras 25 segundos y bombero enviado a Misión Cumplida.");
                })
                .then();
    }
}
