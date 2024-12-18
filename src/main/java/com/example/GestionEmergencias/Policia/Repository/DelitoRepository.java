package com.example.GestionEmergencias.Policia.Repository;

import com.example.GestionEmergencias.Policia.Entity.Delito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelitoRepository extends JpaRepository<Delito, Long> {
}
