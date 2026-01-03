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

    // NOVO POLJE ZA SLIKU
    @Column(length = 500)
    private String slika;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;
    
    public Proizvod() {
    }

    // Ažuriran konstruktor sa svim poljima (uključujući sliku)
    public Proizvod(Long id, String naziv, double cena, int velicina, int kolicinaNaStanju, boolean obrisan,
            String slika, Kategorija kategorija) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.velicina = velicina;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.obrisan = obrisan;
        this.slika = slika;
        this.kategorija = kategorija;
    }

    // Ažuriran konstruktor bez ID-a
    public Proizvod(String naziv, double cena, int velicina, int kolicinaNaStanju, boolean obrisan,
            String slika, Kategorija kategorija) {
        this.naziv = naziv;
        this.cena = cena;
        this.velicina = velicina;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.obrisan = obrisan;
        this.slika = slika;
        this.kategorija = kategorija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getVelicina() {
        return velicina;
    }

    public void setVelicina(int velicina) {
        this.velicina = velicina;
    }

    public int getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(int kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    // GETER I SETER ZA SLIKU KOJI SU NEDOSTAJALI
    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}