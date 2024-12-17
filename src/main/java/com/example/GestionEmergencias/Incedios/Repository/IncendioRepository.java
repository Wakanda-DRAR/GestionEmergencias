package com.example.GestionEmergencias.Incedios.Repository;

import com.example.GestionEmergencias.Incedios.Entity.Incendio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncendioRepository extends JpaRepository<Incendio, Long> {
}