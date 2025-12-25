package com.vts.prodavnicaobuce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

}
