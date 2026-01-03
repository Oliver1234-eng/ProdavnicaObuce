package com.vts.prodavnicaobuce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

	List<Proizvod> findByObrisanFalseAndNazivContainingIgnoreCase(String naziv);

    List<Proizvod> findByObrisanFalseAndKategorija_NazivIgnoreCase(String nazivKategorije);

    List<Proizvod> findByObrisanFalseAndCenaBetween(double min, double max);

    List<Proizvod> findByObrisanFalseAndNazivContainingIgnoreCaseAndKategorija_NazivIgnoreCase(
            String naziv,
            String kategorija
    );
    
    List<Proizvod> findByObrisanFalse();

    Optional<Proizvod> findByIdAndObrisanFalse(Long id);
}
