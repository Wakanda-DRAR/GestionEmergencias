package com.example.GestionEmergencias.Policia.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreDelincuente;
    private int aniosCarcel;
    private String nombreCarcel;
    private String pais;
    private String ciudad;

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

    public int getAniosCarcel() {
        return aniosCarcel;
    }

    public void setAniosCarcel(int aniosCarcel) {
        this.aniosCarcel = aniosCarcel;
    }

    public String getNombreCarcel() {
        return nombreCarcel;
    }

    public void setNombreCarcel(String nombreCarcel) {
        this.nombreCarcel = nombreCarcel;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}