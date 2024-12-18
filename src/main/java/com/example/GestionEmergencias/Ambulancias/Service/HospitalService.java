package com.example.GestionEmergencias.Ambulancias.Service;

import com.example.GestionEmergencias.Ambulancias.Entity.Hospital;
import com.example.GestionEmergencias.Ambulancias.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
}