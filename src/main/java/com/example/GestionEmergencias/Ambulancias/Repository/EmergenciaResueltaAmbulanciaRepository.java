package com.example.GestionEmergencias.Ambulancias.Repository;

import com.example.GestionEmergencias.Ambulancias.Entity.EmergenciaResueltaAmbulancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenciaResueltaAmbulanciaRepository extends JpaRepository<EmergenciaResueltaAmbulancia, Long> {
}