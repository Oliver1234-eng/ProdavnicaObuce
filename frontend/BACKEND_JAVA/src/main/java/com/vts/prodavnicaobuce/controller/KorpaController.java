package com.vts.prodavnicaobuce.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.DodajUKorpuRequest;
import com.vts.prodavnicaobuce.dto.KorpaDTO;
import com.vts.prodavnicaobuce.mapper.KorpaMapper;
import com.vts.prodavnicaobuce.model.Korpa;
import com.vts.prodavnicaobuce.service.KorpaService;

@RestController
@RequestMapping("/korpa")
public class KorpaController {

    private final KorpaService korpaService;
    private final KorpaMapper korpaMapper;

    public KorpaController(KorpaService korpaService, KorpaMapper korpaMapper) {
		this.korpaService = korpaService;
		this.korpaMapper = korpaMapper;
	}

    // Endpoint za dodavanje proizvoda u korpu - neregistrovani korisnik
    @PostMapping("/dodaj")
    public ResponseEntity<?> dodajUKorpu(
            @RequestHeader("X-SESSION-ID") String sessionId,
            @RequestParam Long proizvodId,
            @RequestParam int kolicina) {

        korpaService.dodajProizvod(sessionId, proizvodId, kolicina);

        return ResponseEntity.ok(
            Map.of("message", "Proizvod uspešno dodat u korpu")
        );
    }

    // Endpoint za pregled korpe - neregistrovani korisnik
    @GetMapping("/korpa")
    public KorpaDTO getKorpa(@RequestHeader("X-SESSION-ID") String sessionId) {
        Korpa korpa = korpaService.pregledKorpe(sessionId);

        if (korpa == null || korpa.getStavke() == null || korpa.getStavke().isEmpty()) {
            KorpaDTO praznaKorpa = new KorpaDTO();
            praznaKorpa.setSessionId(sessionId);
            praznaKorpa.setStavke(Collections.emptyList());
            praznaKorpa.setUkupnaCena(0.0);
            return praznaKorpa;
        }

        return korpaMapper.mapToDTO(korpa);
    }
    
    // Endpoint za dodavanje proizvoda u korpu - registrovani korisnik (kupac)
    @PostMapping("/kupac/dodaj")
    public ResponseEntity<?> dodajUKorpu(
            @RequestBody DodajUKorpuRequest request) {

        korpaService.dodajProizvod(
            request.getProizvodId(),
            request.getKolicina()
        );

        return ResponseEntity.ok(
            Map.of("message", "Proizvod uspešno dodat u korpu")
        );
    }
    
    // Endpoint za pregled korpe - registrovani korisnik (kupac)
    @GetMapping("/kupac/korpa")
    public ResponseEntity<KorpaDTO> pregledKorpe() {

        Korpa korpa = korpaService.getKorpaZaUlogovanogKorisnika();

        KorpaDTO dto = korpaMapper.mapToDTO(korpa);

        return ResponseEntity.ok(dto);
    }
}
