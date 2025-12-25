package com.vts.prodavnicaobuce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.ProizvodDTO;
import com.vts.prodavnicaobuce.model.Kategorija;
import com.vts.prodavnicaobuce.model.Proizvod;
import com.vts.prodavnicaobuce.repository.KategorijaRepository;
import com.vts.prodavnicaobuce.service.ProizvodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodController {

    private final ProizvodService proizvodService;
    private final KategorijaRepository kategorijaRepository;

    public ProizvodController(ProizvodService proizvodService, KategorijaRepository kategorijaRepository) {
        this.proizvodService = proizvodService;
        this.kategorijaRepository = kategorijaRepository;
    }

    @GetMapping
    public List<ProizvodDTO> getAllProizvodi() {
        return proizvodService.getAllProizvodi()
                .stream()
                .map(ProizvodDTO::new)
                .collect(Collectors.toList());
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> dodajProizvod(@RequestBody @Valid ProizvodDTO request) {

    	Kategorija kategorija = kategorijaRepository.findByNaziv(request.getKategorija())
                .orElseThrow(() -> new RuntimeException("Kategorija '" + request.getKategorija() + "' ne postoji"));

        Proizvod proizvod = new Proizvod();
        proizvod.setNaziv(request.getNaziv());
        proizvod.setCena(request.getCena());
        proizvod.setVelicina(request.getVelicina());
        proizvod.setKolicinaNaStanju(request.getKolicinaNaStanju());
        proizvod.setKategorija(kategorija);
        proizvod.setObrisan(false);

        proizvodService.save(proizvod);

        return ResponseEntity.ok("Proizvod uspešno dodat");
    }
}
