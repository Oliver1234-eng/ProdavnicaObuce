package com.vts.prodavnicaobuce.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.vts.prodavnicaobuce.dto.ProizvodDTO;
import com.vts.prodavnicaobuce.model.Kategorija;
import com.vts.prodavnicaobuce.model.Proizvod;
import com.vts.prodavnicaobuce.repository.KategorijaRepository;
import com.vts.prodavnicaobuce.repository.ProizvodRepository;

@Service
public class ProizvodService {

    private final ProizvodRepository proizvodRepository;
    private final KategorijaRepository kategorijaRepository;

    public ProizvodService(ProizvodRepository proizvodRepository, KategorijaRepository kategorijaRepository) {
        this.proizvodRepository = proizvodRepository;
        this.kategorijaRepository = kategorijaRepository;
    }

    public List<Proizvod> getAllProizvodi() {
        return proizvodRepository.findAll();
    }
    
    public void save(Proizvod proizvod) {
        proizvodRepository.save(proizvod);
    }
    
    public List<Proizvod> pretraga(String naziv, String kategorija, Double minCena, Double maxCena) {
        if (naziv != null && kategorija != null) {
            return proizvodRepository.findByObrisanFalseAndNazivContainingIgnoreCaseAndKategorija_NazivIgnoreCase(naziv, kategorija);
        }
        if (naziv != null) {
            return proizvodRepository.findByObrisanFalseAndNazivContainingIgnoreCase(naziv);
        }
        if (kategorija != null) {
            return proizvodRepository.findByObrisanFalseAndKategorija_NazivIgnoreCase(kategorija);
        }
        if (minCena != null && maxCena != null) {
            return proizvodRepository.findByObrisanFalseAndCenaBetween(minCena, maxCena);
        }
        return proizvodRepository.findByObrisanFalse();
    }
    
    /**
     * NOVA KOMPLETNA METODA ZA IZMENU
     * Koristi ProizvodDTO i ažurira SVA polja uključujući veličinu i sliku.
     */
    public void azurirajKompletanProizvod(Long id, ProizvodDTO dto) {
        Proizvod p = proizvodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proizvod sa ID " + id + " nije pronađen"));

        if (p.isObrisan()) {
            throw new RuntimeException("Nije moguće izmeniti obrisan proizvod");
        }

        // Ažuriranje svih polja
        p.setNaziv(dto.getNaziv());
        p.setCena(dto.getCena());
        p.setVelicina(dto.getVelicina()); 
        p.setKolicinaNaStanju(dto.getKolicinaNaStanju());
        p.setSlika(dto.getSlika());

        // Ažuriranje kategorije po nazivu
        if (dto.getKategorija() != null) {
            Kategorija kat = kategorijaRepository.findByNaziv(dto.getKategorija())
                    .orElseGet(() -> {
                        Kategorija nova = new Kategorija();
                        nova.setNaziv(dto.getKategorija());
                        return kategorijaRepository.save(nova);
                    });
            p.setKategorija(kat);
        }

        proizvodRepository.save(p);
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