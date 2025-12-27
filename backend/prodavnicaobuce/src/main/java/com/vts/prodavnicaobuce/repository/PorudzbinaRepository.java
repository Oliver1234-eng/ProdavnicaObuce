package com.vts.prodavnicaobuce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {

	List<Porudzbina> findByKupac(Korisnik kupac);
}
