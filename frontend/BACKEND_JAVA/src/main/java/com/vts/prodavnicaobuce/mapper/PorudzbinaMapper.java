package com.vts.prodavnicaobuce.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vts.prodavnicaobuce.dto.PorudzbinaDTO;
import com.vts.prodavnicaobuce.dto.StavkaPorudzbineDTO;
import com.vts.prodavnicaobuce.model.Porudzbina;

@Component
public class PorudzbinaMapper {

	public static PorudzbinaDTO toDTO(Porudzbina porudzbina) {
		
        PorudzbinaDTO dto = new PorudzbinaDTO();
        dto.setId(porudzbina.getId());
        dto.setDatumPorudzbine(porudzbina.getDatumPorudzbine());
        dto.setStatus(porudzbina.getStatus().name());
        dto.setUkupnaCena(porudzbina.getUkupnaCena());

        if (porudzbina.getKupac() != null) {
            dto.setKupacUsername(porudzbina.getKupac().getUsername());
        }
        
        if (porudzbina.getProdavac() != null) {
            dto.setProdavacUsername(porudzbina.getProdavac().getUsername());
        }

        dto.setNeregIme(porudzbina.getNeregIme());
        dto.setNeregPrezime(porudzbina.getNeregPrezime());
        dto.setNeregAdresa(porudzbina.getNeregAdresa());
        dto.setNeregEmail(porudzbina.getNeregEmail());
        dto.setNeregTelefon(porudzbina.getNeregTelefon());

        List<StavkaPorudzbineDTO> stavke = porudzbina.getStavke().stream()
            .map(s -> {
                StavkaPorudzbineDTO sdto = new StavkaPorudzbineDTO();
                sdto.setProizvodId(s.getProizvod().getId());
                sdto.setNazivProizvoda(s.getProizvod().getNaziv());
                sdto.setKolicina(s.getKolicina());
                sdto.setCena(s.getCena());
                return sdto;
            }).toList();

        dto.setStavke(stavke);

        return dto;
    }
	
	public PorudzbinaDTO mapToDTO(Porudzbina porudzbina) {
        PorudzbinaDTO dto = new PorudzbinaDTO();
        dto.setId(porudzbina.getId());
        dto.setDatumPorudzbine(porudzbina.getDatumPorudzbine());
        dto.setStatus(porudzbina.getStatus().name());
        dto.setUkupnaCena(porudzbina.getUkupnaCena());
        if (porudzbina.getKupac() != null) {
            dto.setKupacUsername(porudzbina.getKupac().getUsername());
        }

        List<StavkaPorudzbineDTO> stavke = porudzbina.getStavke().stream()
                .map(s -> {
                    StavkaPorudzbineDTO sdto = new StavkaPorudzbineDTO();
                    sdto.setProizvodId(s.getProizvod().getId());
                    sdto.setNazivProizvoda(s.getProizvod().getNaziv());
                    sdto.setKolicina(s.getKolicina());
                    sdto.setCena(s.getCena());
                    return sdto;
                })
                .toList();

        dto.setStavke(stavke);

        return dto;
    }
}
