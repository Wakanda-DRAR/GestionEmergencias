package com.example.GestionEmergencias.Incedios.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmergenciaResuelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long incendioId;
    private String ciudad;
    private String pais;
    private String calle;
    private String fechaResolucion;
    private Long bomberoId;
    private String nombreBombero;
    private String nombreDepartamento;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncendioId() {
        return incendioId;
    }

    public void setIncendioId(Long incendioId) {
        this.incendioId = incendioId;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Long getBomberoId() {
        return bomberoId;
    }

    public void setBomberoId(Long bomberoId) {
        this.bomberoId = bomberoId;
    }

    public String getNombreBombero() {
        return nombreBombero;
    }

    public void setNombreBombero(String nombreBombero) {
        this.nombreBombero = nombreBombero;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

}
