package com.vts.prodavnicaobuce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    public StavkaKorpe() {
    	
    }

	public StavkaKorpe(Long id, int kolicina, double cena, Korpa korpa, Proizvod proizvod) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.cena = cena;
		this.korpa = korpa;
		this.proizvod = proizvod;
	}

	public StavkaKorpe(int kolicina, double cena, Korpa korpa, Proizvod proizvod) {
		super();
		this.kolicina = kolicina;
		this.cena = cena;
		this.korpa = korpa;
		this.proizvod = proizvod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
}
