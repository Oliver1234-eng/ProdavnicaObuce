package com.vts.prodavnicaobuce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Korpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kupac_id", nullable = false)
    private Korisnik kupac;

    @OneToMany(
        mappedBy = "korpa",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<StavkaKorpe> stavke;
}
