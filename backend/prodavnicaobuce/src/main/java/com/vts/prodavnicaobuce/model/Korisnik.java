package com.vts.prodavnicaobuce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "korisnici")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String adresa;
    
    @Column(nullable = false)
    private String brojTelefona;

    @Column(nullable = false)
    private boolean blokiran = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UlogaKorisnika ulogaKorisnika;

    @OneToOne(mappedBy = "kupac", cascade = CascadeType.ALL)
    private Korpa korpa;

    @OneToMany(mappedBy = "kupac")
    private List<Porudzbina> porudzbine;

    @OneToMany(mappedBy = "prodavac")
    private List<Porudzbina> obradjenePorudzbine;
}
