package com.vts.prodavnicaobuce.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vts.prodavnicaobuce.dto.KorisnikDTO;
import com.vts.prodavnicaobuce.dto.RegisterRequest;
import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.UlogaKorisnika;
import com.vts.prodavnicaobuce.repository.KorisnikRepository;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public KorisnikService(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerKupac(RegisterRequest request) {
        if (korisnikRepository.existsByUsername(request.username)) {
            throw new RuntimeException("Username već postoji");
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.ime);
        korisnik.setPrezime(request.prezime);
        korisnik.setEmail(request.email);
        korisnik.setUsername(request.username);
        korisnik.setPassword(passwordEncoder.encode(request.password));
        korisnik.setAdresa(request.adresa);
        korisnik.setBrojTelefona(request.brojTelefona);
        korisnik.setBlokiran(false);

        // Sada "request.uloga" više neće izbacivati grešku jer smo je dodali u RegisterRequest
        if (request.uloga != null && !request.uloga.isEmpty()) {
            try {
                korisnik.setUlogaKorisnika(UlogaKorisnika.valueOf(request.uloga.toUpperCase()));
            } catch (IllegalArgumentException e) {
                korisnik.setUlogaKorisnika(UlogaKorisnika.KUPAC);
            }
        } else {
            korisnik.setUlogaKorisnika(UlogaKorisnika.KUPAC);
        }

        korisnikRepository.save(korisnik);
    }

    public Korisnik findByUsername(String username) {
        return korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
    }
}