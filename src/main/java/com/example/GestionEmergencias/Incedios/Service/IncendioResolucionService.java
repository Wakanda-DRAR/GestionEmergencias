package com.example.GestionEmergencias.Incedios.Service;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import com.example.GestionEmergencias.Incedios.Entity.EmergenciaResuelta;
import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import com.example.GestionEmergencias.Incedios.Entity.MisionCumplida;
import com.example.GestionEmergencias.Incedios.Repository.BomberoRepository;
import com.example.GestionEmergencias.Incedios.Repository.EmergenciaResueltaRepository;
import com.example.GestionEmergencias.Incedios.Repository.IncendioRepository;
import com.example.GestionEmergencias.Incedios.Repository.MisionCumplidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private MisionCumplidaRepository misionCumplidaRepository;

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
            jdbcTemplate.execute("TRUNCATE TABLE mision_cumplida");
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("Bases de datos limpiadas al iniciar la aplicación.");
        };
    }

    public void resolverIncendioYBombero(Incendio incendio, Bombero bombero) {
        // Guardar en EmergenciaResuelta
        EmergenciaResuelta resuelta = new EmergenciaResuelta();
        resuelta.setIncendioId(incendio.getId());
        resuelta.setCiudad(incendio.getCiudad());
        resuelta.setFechaResolucion(java.time.LocalDate.now().toString());
        emergenciaResueltaRepository.save(resuelta);

        // Guardar en MisionCumplida
        MisionCumplida mision = new MisionCumplida();
        mision.setBomberoId(bombero.getId());
        mision.setNombreBombero(bombero.getNombreBombero());
        mision.setFechaFinalizacion(java.time.LocalDate.now().toString());
        misionCumplidaRepository.save(mision);

        // Eliminar los registros originales
        incendioRepository.deleteById(incendio.getId());
        bomberoRepository.deleteById(bombero.getId());

        System.out.println("Incendio resuelto y bombero enviado a Misión Cumplida.");
    }
}
