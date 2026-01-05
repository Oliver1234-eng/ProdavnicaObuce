package com.vts.prodavnicaobuce.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.Korpa;
import com.vts.prodavnicaobuce.model.Proizvod;
import com.vts.prodavnicaobuce.model.StavkaKorpe;
import com.vts.prodavnicaobuce.repository.KorisnikRepository;
import com.vts.prodavnicaobuce.repository.KorpaRepository;
import com.vts.prodavnicaobuce.repository.ProizvodRepository;

import jakarta.transaction.Transactional;

@Service
public class KorpaService {

    private final KorpaRepository korpaRepository;
    
    private final ProizvodRepository proizvodRepository;
    
    private final KorisnikRepository korisnikRepository;

    public KorpaService(KorpaRepository korpaRepository, ProizvodRepository proizvodRepository,
			KorisnikRepository korisnikRepository) {
		super();
		this.korpaRepository = korpaRepository;
		this.proizvodRepository = proizvodRepository;
		this.korisnikRepository = korisnikRepository;
	}

    public Korpa dodajProizvod(String sessionId, Long proizvodId, int kolicina) {

        Korpa korpa = korpaRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    Korpa nova = new Korpa();
                    nova.setSessionId(sessionId);
                    nova.setStavke(new ArrayList<>());
                    return korpaRepository.save(nova);
                });

        Proizvod proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));

        StavkaKorpe stavka = korpa.getStavke().stream()
                .filter(s -> s.getProizvod().getId().equals(proizvodId))
                .findFirst()
                .orElse(null);

        if (stavka == null) {
            StavkaKorpe nova = new StavkaKorpe();
            nova.setProizvod(proizvod);
            nova.setKolicina(kolicina);
            nova.setCena(proizvod.getCena());
            nova.setKorpa(korpa);
            korpa.getStavke().add(nova);
        } else {
            stavka.setKolicina(stavka.getKolicina() + kolicina);
        }

        return korpaRepository.save(korpa);
    }


	public Korpa pregledKorpe(String sessionId) {
	    return korpaRepository.findBySessionId(sessionId).orElse(null);
	}
    
    public Korpa getKorpaZaUlogovanogKorisnika() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Korisnik kupac = korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));

        return korpaRepository.findByKupac(kupac)
                .orElseGet(() -> {
                    Korpa k = new Korpa();
                    k.setKupac(kupac);
                    k.setStavke(new ArrayList<>());
                    return korpaRepository.save(k);
                });
    }

    public void dodajProizvod(Long proizvodId, int kolicina) {

        Korpa korpa = getKorpaZaUlogovanogKorisnika();

        Proizvod proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));

        Optional<StavkaKorpe> postojeca = korpa.getStavke().stream()
                .filter(s -> s.getProizvod().getId().equals(proizvodId))
                .findFirst();

        if (postojeca.isPresent()) {
            StavkaKorpe stavka = postojeca.get();
            stavka.setKolicina(stavka.getKolicina() + kolicina);
        } else {
            StavkaKorpe stavka = new StavkaKorpe();
            stavka.setProizvod(proizvod);
            stavka.setKolicina(kolicina);
            stavka.setCena(proizvod.getCena());
            stavka.setKorpa(korpa);
            korpa.getStavke().add(stavka);
        }

        korpaRepository.save(korpa);
    }
    
    public Korpa findBySessionId(String sessionId) {
        return korpaRepository.findBySessionId(sessionId)
                .orElse(null);
    }
    
    @Transactional
    public void clearKorpa(Korpa korpa) {
        if (korpa != null) {
            korpa.getStavke().clear();
            korpaRepository.delete(korpa);
        }
    }
}
