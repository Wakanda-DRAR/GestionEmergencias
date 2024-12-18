package com.example.GestionEmergencias.Policia.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmergenciaResueltaPolicia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long delitoId;
    private String ciudad;
    private String pais;
    private String calle;
    private String fechaResolucion;
    private String policiaId;
    private String nombrePolicia;
    private String nombreDepartamento;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDelitoId() {
        return delitoId;
    }
    public void setDelitoId(Long delitoId) {
        this.delitoId = delitoId;
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
    public String getFechaResolucion() {
        return fechaResolucion;
    }
    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    public String getPoliciaId() {
        return policiaId;
    }
    public void setPoliciaId(String policiaId) {
        this.policiaId = policiaId;
    }
    public String getNombrePolicia() {
        return nombrePolicia;
    }
    public void setNombrePolicia(String nombrePolicia) {
        this.nombrePolicia = nombrePolicia;
    }
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}