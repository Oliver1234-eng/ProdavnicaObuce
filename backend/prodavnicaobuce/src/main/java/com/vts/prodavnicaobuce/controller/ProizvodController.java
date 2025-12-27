package com.vts.prodavnicaobuce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.ProizvodDTO;
import com.vts.prodavnicaobuce.dto.ProizvodUpdateDTO;
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
    public List<ProizvodDTO> sviProizvodi() {
        return proizvodService.sviAktivni()
                .stream()
                .map(ProizvodDTO::fromEntity)
                .toList();
    }
    
    // Endpoint za dodavanje novog proizvoda od strane administratora
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
    
    // Endpoint za pretragu proizvoda po kriterijumima
    @GetMapping("/pretraga")
    public List<ProizvodDTO> pretraga(
            @RequestParam(required = false) String naziv,
            @RequestParam(required = false) String kategorija,
            @RequestParam(required = false) Double minCena,
            @RequestParam(required = false) Double maxCena
    ) {
        return proizvodService.pretraga(naziv, kategorija, minCena, maxCena)
                .stream()
                .map(ProizvodDTO::fromEntity)
                .toList();
    }
    
    // Endpoint za izmenu cene i/ili dodavanje dodatne kolicine proizvoda od strane administratora
    @PutMapping("/izmena/{id}")
    public ResponseEntity<String> izmeniCenuIKolicinu(
            @PathVariable Long id,
            @RequestBody @Valid ProizvodUpdateDTO dto
    ) {
        proizvodService.izmeniCenuIKolicinu(id, dto);
        return ResponseEntity.ok("Proizvod uspešno ažuriran");
    }
    
    // Endpoint za brisanje proizvoda od strane administratora
    @DeleteMapping("obrisi/{id}")
    public ResponseEntity<String> obrisi(@PathVariable Long id) {
        proizvodService.obrisiProizvod(id);
        return ResponseEntity.ok("Proizvod uspešno obrisan");
    }
}
