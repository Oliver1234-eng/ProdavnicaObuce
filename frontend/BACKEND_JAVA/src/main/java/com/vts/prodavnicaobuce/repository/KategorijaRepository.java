package com.vts.prodavnicaobuce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

	Optional<Kategorija> findByNaziv(String naziv);
}
