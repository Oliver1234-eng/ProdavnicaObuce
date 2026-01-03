package com.vts.prodavnicaobuce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.KorisnikDTO;
import com.vts.prodavnicaobuce.service.KorisnikService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final KorisnikService korisnikService;

    public AdminController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    // Endpoint za pregled svih korisnika od strane administratora
    @GetMapping("/korisnici")
    public List<KorisnikDTO> getAdminiIProdavci() {
        return korisnikService.getAdminiIProdavci();
    }

    // Endpoint za blokiranje korisnika od strane administratora
    @PutMapping("korisnici/{id}/blokiraj")
    public ResponseEntity<?> blokiraj(@PathVariable Long id) {
        korisnikService.blokirajKorisnika(id);
        return ResponseEntity.ok("Korisnik je blokiran");
    }
}
