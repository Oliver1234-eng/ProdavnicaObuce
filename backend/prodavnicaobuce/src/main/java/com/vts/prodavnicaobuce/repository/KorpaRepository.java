package com.vts.prodavnicaobuce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.Korpa;

public interface KorpaRepository extends JpaRepository<Korpa, Long> {

	Optional<Korpa> findBySessionId(String sessionId);
	
	Optional<Korpa> findByKupac(Korisnik kupac);
}
