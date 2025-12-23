package com.vts.prodavnicaobuce.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Porudzbina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime datumPorudzbine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPorudzbine status;

    @Column(nullable = false)
    private double ukupnaCena;

    @ManyToOne
    @JoinColumn(name = "kupac_id", nullable = false)
    private Korisnik kupac;

    @ManyToOne
    @JoinColumn(name = "prodavac_id")
    private Korisnik prodavac;

    @OneToMany(
        mappedBy = "porudzbina",
        cascade = CascadeType.ALL
    )
    private List<StavkaPorudzbine> stavke;
}
