package com.vts.prodavnicaobuce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StavkaKorpe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int kolicina;

    @Column(nullable = false)
    private double cena;

    @ManyToOne
    @JoinColumn(name = "korpa_id", nullable = false)
    private Korpa korpa;

    @ManyToOne
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvod proizvod;
}
