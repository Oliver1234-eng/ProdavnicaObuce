package com.vts.prodavnicaobuce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.dto.PorudzbinaDTO;
import com.vts.prodavnicaobuce.mapper.PorudzbinaMapper;
import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.Korpa;
import com.vts.prodavnicaobuce.model.Porudzbina;
import com.vts.prodavnicaobuce.model.StatusPorudzbine;
import com.vts.prodavnicaobuce.model.StavkaPorudzbine;
import com.vts.prodavnicaobuce.repository.KorisnikRepository;
import com.vts.prodavnicaobuce.repository.PorudzbinaRepository;
import com.vts.prodavnicaobuce.security.jwt.JwtService;

import jakarta.transaction.Transactional;

@Service
public class PorudzbinaService {

    private final KorpaService korpaService;
    
    private final PorudzbinaRepository porudzbinaRepository;
    
    private final KorisnikRepository korisnikRepository;
    
    @Autowired
    private JwtService jwtService;

	public PorudzbinaService(KorpaService korpaService, PorudzbinaRepository porudzbinaRepository,
			KorisnikRepository korisnikRepository, JwtService jwtService) {
		super();
		this.korpaService = korpaService;
		this.porudzbinaRepository = porudzbinaRepository;
		this.korisnikRepository = korisnikRepository;
		this.jwtService = jwtService;
	}

	public List<PorudzbinaDTO> getSvePorudzbine() {
        return porudzbinaRepository.findAll().stream()
                                   .map(PorudzbinaMapper::toDTO)
                                   .toList();
    }
	
	public List<Porudzbina> getPorudzbineZaUlogovanogKupca() {
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    Korisnik kupac = korisnikRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Kupac ne postoji"));
	    return porudzbinaRepository.findByKupac(kupac);
	}
	
	public Porudzbina promeniStatusNaPoslata(Long porudzbinaId, String usernameProdavca) {

        Porudzbina porudzbina = porudzbinaRepository.findById(porudzbinaId)
                .orElseThrow(() -> new RuntimeException("Porudžbina ne postoji"));

        if (porudzbina.getStatus() != StatusPorudzbine.KREIRANA) {
            throw new RuntimeException("Status porudžbine se ne može promeniti");
        }

        if (porudzbina.getProdavac() == null) {
            Korisnik prodavac = korisnikRepository.findByUsername(usernameProdavca)
                    .orElseThrow(() -> new RuntimeException("Prodavac ne postoji"));
            porudzbina.setProdavac(prodavac);
        }

        porudzbina.setStatus(StatusPorudzbine.POSLATA);

        return porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina naruciKorpuGuest(
            String sessionId,
            String ime,
            String prezime,
            String email,
            String telefon,
            String adresa) {

        Korpa korpa = korpaService.findBySessionId(sessionId);
        if (korpa == null || korpa.getStavke().isEmpty()) {
            throw new RuntimeException("Korpa je prazna ili ne postoji");
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setDatumPorudzbine(LocalDateTime.now());
        porudzbina.setStatus(StatusPorudzbine.KREIRANA);
        porudzbina.setUkupnaCena(
                korpa.getStavke().stream()
                        .mapToDouble(s -> s.getCena() * s.getKolicina())
                        .sum()
        );

        porudzbina.setNeregIme(ime);
        porudzbina.setNeregPrezime(prezime);
        porudzbina.setNeregEmail(email);
        porudzbina.setNeregTelefon(telefon);
        porudzbina.setNeregAdresa(adresa);

        List<StavkaPorudzbine> stavke = korpa.getStavke().stream()
                .map(s -> {
                    StavkaPorudzbine sp = new StavkaPorudzbine();
                    sp.setPorudzbina(porudzbina);
                    sp.setProizvod(s.getProizvod());
                    sp.setKolicina(s.getKolicina());
                    sp.setCena(s.getCena());
                    return sp;
                })
                .toList();

        porudzbina.setStavke(stavke);

        porudzbinaRepository.save(porudzbina);

        korpaService.clearKorpa(korpa);

        return porudzbina;
    }
    
    public Porudzbina naruciKorpuRegistrovani() {
        Korpa korpa = korpaService.getKorpaZaUlogovanogKorisnika();

        if (korpa.getStavke().isEmpty()) {
            throw new RuntimeException("Korpa je prazna");
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setKupac(korpa.getKupac());
        porudzbina.setDatumPorudzbine(LocalDateTime.now());
        porudzbina.setStatus(StatusPorudzbine.KREIRANA);
        porudzbina.setUkupnaCena(
            korpa.getStavke().stream()
                 .mapToDouble(s -> s.getCena() * s.getKolicina())
                 .sum()
        );

        List<StavkaPorudzbine> stavke = korpa.getStavke().stream()
            .map(s -> {
                StavkaPorudzbine sp = new StavkaPorudzbine();
                sp.setProizvod(s.getProizvod());
                sp.setKolicina(s.getKolicina());
                sp.setCena(s.getCena());
                sp.setPorudzbina(porudzbina);
                return sp;
            })
            .toList();

        porudzbina.setStavke(stavke);

        porudzbinaRepository.save(porudzbina);

        korpaService.clearKorpa(korpa);

        return porudzbina;
    }
}

