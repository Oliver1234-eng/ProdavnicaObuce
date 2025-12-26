package com.vts.prodavnicaobuce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.dto.ProizvodUpdateDTO;
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
    
    public List<Proizvod> pretraga(String naziv, String kategorija, Double minCena, Double maxCena) {

        if (naziv != null && kategorija != null) {
            return proizvodRepository
                    .findByObrisanFalseAndNazivContainingIgnoreCaseAndKategorija_NazivIgnoreCase(
                            naziv, kategorija);
        }

        if (naziv != null) {
            return proizvodRepository
                    .findByObrisanFalseAndNazivContainingIgnoreCase(naziv);
        }

        if (kategorija != null) {
            return proizvodRepository
                    .findByObrisanFalseAndKategorija_NazivIgnoreCase(kategorija);
        }

        if (minCena != null && maxCena != null) {
            return proizvodRepository
                    .findByObrisanFalseAndCenaBetween(minCena, maxCena);
        }

        return proizvodRepository.findAll()
                .stream()
                .filter(p -> !p.isObrisan())
                .toList();
    }
    
    public void izmeniCenuIKolicinu(Long proizvodId, ProizvodUpdateDTO dto) {

        Proizvod proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));

        if (proizvod.isObrisan()) {
            throw new RuntimeException("Proizvod je obrisan");
        }
        
        proizvod.setCena(dto.getCena());

        proizvod.setKolicinaNaStanju(
                proizvod.getKolicinaNaStanju() + dto.getDodatnaKolicina()
        );

        proizvodRepository.save(proizvod);
    }
    
    public void obrisiProizvod(Long id) {

        Proizvod proizvod = proizvodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));

        if (proizvod.isObrisan()) {
            throw new RuntimeException("Proizvod je već obrisan");
        }

        proizvod.setObrisan(true);
        proizvodRepository.save(proizvod);
    }

    public List<Proizvod> sviAktivni() {
        return proizvodRepository.findByObrisanFalse();
    }
}
