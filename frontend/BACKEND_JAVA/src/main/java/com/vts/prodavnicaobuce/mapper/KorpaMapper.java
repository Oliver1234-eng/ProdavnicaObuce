package com.vts.prodavnicaobuce.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vts.prodavnicaobuce.dto.KorpaDTO;
import com.vts.prodavnicaobuce.dto.StavkaKorpeDTO;
import com.vts.prodavnicaobuce.model.Korpa;

@Component
public class KorpaMapper {

    public KorpaDTO mapToDTO(Korpa korpa) {

        KorpaDTO dto = new KorpaDTO();
        dto.setSessionId(korpa.getSessionId());

        List<StavkaKorpeDTO> stavke = korpa.getStavke().stream()
            .map(s -> {
                StavkaKorpeDTO sdto = new StavkaKorpeDTO();
                sdto.setProizvodId(s.getProizvod().getId());
                sdto.setNazivProizvoda(s.getProizvod().getNaziv());
                sdto.setKolicina(s.getKolicina());
                sdto.setCena(s.getCena());
                return sdto;
            })
            .toList();

        dto.setStavke(stavke);

        double ukupno = stavke.stream()
            .mapToDouble(s -> s.getCena() * s.getKolicina())
            .sum();

        dto.setUkupnaCena(ukupno);

        return dto;
    }
}

