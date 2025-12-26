package com.vts.prodavnicaobuce.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.dto.KorisnikDTO;
import com.vts.prodavnicaobuce.dto.RegisterRequest;
import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.UlogaKorisnika;
import com.vts.prodavnicaobuce.repository.KorisnikRepository;

import lombok.RequiredArgsConstructor;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public KorisnikService(KorisnikRepository korisnikRepository,
                           PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerKupac(RegisterRequest request) {

        if (korisnikRepository.existsByUsername(request.username)) {
            throw new RuntimeException("Username već postoji");
        }

        if (korisnikRepository.existsByEmail(request.email)) {
            throw new RuntimeException("Email već postoji");
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.ime);
        korisnik.setPrezime(request.prezime);
        korisnik.setEmail(request.email);
        korisnik.setUsername(request.username);
        korisnik.setPassword(
                passwordEncoder.encode(request.password)
        );
        korisnik.setAdresa(request.adresa);
        korisnik.setBrojTelefona(request.brojTelefona);
        korisnik.setUlogaKorisnika(UlogaKorisnika.KUPAC);
        korisnik.setBlokiran(false);

        korisnikRepository.save(korisnik);
    }
    
    public Korisnik findByUsername(String username) {
        return korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
    }
    
    public List<KorisnikDTO> getAdminiIProdavci() {
        return korisnikRepository
                .findByUlogaKorisnikaIn(List.of(UlogaKorisnika.PRODAVAC, UlogaKorisnika.KUPAC))
                .stream()
                .map(KorisnikDTO::fromEntity)
                .toList();
    }

    public void blokirajKorisnika(Long id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));

        korisnik.setBlokiran(true);
        korisnikRepository.save(korisnik);
    }
}
