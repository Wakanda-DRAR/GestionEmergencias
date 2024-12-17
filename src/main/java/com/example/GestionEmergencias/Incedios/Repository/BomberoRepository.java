package com.example.GestionEmergencias.Incedios.Repository;

import com.example.GestionEmergencias.Incedios.Entity.Bombero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomberoRepository extends JpaRepository<Bombero, Long> {
}
