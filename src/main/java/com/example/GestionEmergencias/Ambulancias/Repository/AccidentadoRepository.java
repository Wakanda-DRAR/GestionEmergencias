package com.example.GestionEmergencias.Ambulancias.Repository;

import com.example.GestionEmergencias.Ambulancias.Entity.Accidentado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentadoRepository extends JpaRepository<Accidentado, Long> {
}