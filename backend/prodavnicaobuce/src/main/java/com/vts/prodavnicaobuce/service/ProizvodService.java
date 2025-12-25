package com.vts.prodavnicaobuce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.model.Proizvod;
import com.vts.prodavnicaobuce.repository.ProizvodRepository;

@Service
public class ProizvodService {

    private final ProizvodRepository proizvodRepository;

    public ProizvodService(ProizvodRepository proizvodRepository) {
        this.proizvodRepository = proizvodRepository;
    }

    public List<Proizvod> getAllProizvodi() {
        return proizvodRepository.findAll();
    }
    
    public void save(Proizvod proizvod) {
        proizvodRepository.save(proizvod);
    }
}
