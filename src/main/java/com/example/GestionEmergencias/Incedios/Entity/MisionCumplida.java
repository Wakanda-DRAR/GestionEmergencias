package com.example.GestionEmergencias.Incedios.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MisionCumplida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bomberoId;
    private String fechaFinalizacion;
    private String nombreBombero;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBomberoId() {
        return bomberoId;
    }

    public void setBomberoId(Long bomberoId) {
        this.bomberoId = bomberoId;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getNombreBombero() {
        return nombreBombero;
    }

    public void setNombreBombero(String nombreBombero) {
        this.nombreBombero = nombreBombero;
    }

}
