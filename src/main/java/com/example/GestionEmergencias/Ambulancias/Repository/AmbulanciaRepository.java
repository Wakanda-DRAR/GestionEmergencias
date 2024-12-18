package com.example.GestionEmergencias.Ambulancias.Repository;

import com.example.GestionEmergencias.Ambulancias.Entity.Ambulancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Long> {
}