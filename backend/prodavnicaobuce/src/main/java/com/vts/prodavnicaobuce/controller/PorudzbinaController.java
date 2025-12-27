package com.vts.prodavnicaobuce.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.NaruciNeregRequest;
import com.vts.prodavnicaobuce.dto.PorudzbinaDTO;
import com.vts.prodavnicaobuce.mapper.PorudzbinaMapper;
import com.vts.prodavnicaobuce.model.Korpa;
import com.vts.prodavnicaobuce.model.Porudzbina;
import com.vts.prodavnicaobuce.model.StatusPorudzbine;
import com.vts.prodavnicaobuce.model.StavkaPorudzbine;
import com.vts.prodavnicaobuce.security.jwt.JwtService;
import com.vts.prodavnicaobuce.service.KorpaService;
import com.vts.prodavnicaobuce.service.PorudzbinaService;

@RestController
@RequestMapping("/porudzbina")
public class PorudzbinaController {

    private final PorudzbinaService porudzbinaService;
    private final PorudzbinaMapper porudzbinaMapper;
    private final KorpaService korpaService;
    
    @Autowired
    private JwtService jwtService;

	public PorudzbinaController(PorudzbinaService porudzbinaService, PorudzbinaMapper porudzbinaMapper,
			KorpaService korpaService, JwtService jwtService) {
		super();
		this.porudzbinaService = porudzbinaService;
		this.porudzbinaMapper = porudzbinaMapper;
		this.korpaService = korpaService;
		this.jwtService = jwtService;
	}

	// Endpoint za pregled svih pristiglih porudzbina od strane prodavca
	@GetMapping("/sve")
    public ResponseEntity<List<PorudzbinaDTO>> pregledPorudzbinaZaProdavca() {
        List<PorudzbinaDTO> porudzbine = porudzbinaService.getSvePorudzbine();
        return ResponseEntity.ok(porudzbine);
    }
	
	// Endpoint za pregled svih svojih porudzbina od strane kupca
	@GetMapping("/mojePorudzbine")
    public ResponseEntity<List<PorudzbinaDTO>> getMojePorudzbine() {
        List<Porudzbina> porudzbine = porudzbinaService.getPorudzbineZaUlogovanogKupca();
        List<PorudzbinaDTO> dtos = porudzbine.stream()
                .map(porudzbinaMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    
	// Endpoint za promenu statusa porudzbine sa KREIRANA na POSLATA od strane prodavca
    @PostMapping("/{id}/poslata")
    public ResponseEntity<Map<String, Object>> oznaciKaoPoslata(
            @PathVariable("id") Long porudzbinaId,
            @RequestHeader("Authorization") String authHeader) {

        try {
            String token = authHeader.substring(7);
            String usernameProdavca = jwtService.extractUsername(token);

            Porudzbina porudzbina = porudzbinaService.promeniStatusNaPoslata(porudzbinaId, usernameProdavca);

            return ResponseEntity.ok(Map.of(
                    "message", "Porudžbina uspešno poslata",
                    "porudzbinaId", porudzbina.getId(),
                    "status", porudzbina.getStatus()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Endpoint za narucivanje sadrzaja iz korpe od strane neregistrovanog korisnika
	@PostMapping("/naruci/nereg")
    public ResponseEntity<Map<String, Object>> naruciNereg(
            @RequestParam String sessionId,
            @RequestBody NaruciNeregRequest request) {
        try {
            Porudzbina porudzbina = porudzbinaService.naruciKorpuGuest(
                    sessionId,
                    request.getIme(),
                    request.getPrezime(),
                    request.getEmail(),
                    request.getTelefon(),
                    request.getAdresa()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Porudžbina je uspešno kreirana");
            response.put("porudzbinaId", porudzbina.getId());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
    
	// Endpoint za narucivanje sadrzaja iz korpe od strane kupca
	@PostMapping("/naruci/kupac")
    public ResponseEntity<Map<String, Object>> naruciRegistrovani() {
        try {
            Porudzbina porudzbina = porudzbinaService.naruciKorpuRegistrovani();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Porudžbina je uspešno kreirana");
            response.put("porudzbinaId", porudzbina.getId());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}

