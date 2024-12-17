package com.example.GestionEmergencias.Policia.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Delito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreDelincuente;
    private String tipoDelito;
    private String fecha;
    private String hora;
    private String ciudad;
    private String pais;
    private String calle;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDelincuente() {
        return nombreDelincuente;
    }

    public void setNombreDelincuente(String nombreDelincuente) {
        this.nombreDelincuente = nombreDelincuente;
    }

    public String getTipoDelito() {
        return tipoDelito;
    }

    public void setTipoDelito(String tipoDelito) {
        this.tipoDelito = tipoDelito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
}