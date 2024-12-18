package com.example.GestionEmergencias.Ambulancias.Repository;

import com.example.GestionEmergencias.Ambulancias.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}