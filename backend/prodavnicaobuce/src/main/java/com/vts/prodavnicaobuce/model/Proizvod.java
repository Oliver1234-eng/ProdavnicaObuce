package com.vts.prodavnicaobuce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private double cena;

    @Column(nullable = false)
    private int velicina;

    @Column(nullable = false)
    private int kolicinaNaStanju;

    @Column(nullable = false)
    private boolean obrisan = false;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;
}
