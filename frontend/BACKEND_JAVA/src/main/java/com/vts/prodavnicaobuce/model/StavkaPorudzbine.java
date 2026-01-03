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
public class StavkaPorudzbine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int kolicina;

    @Column(nullable = false)
    private double cena;

    @ManyToOne
    @JoinColumn(name = "porudzbina_id", nullable = false)
    private Porudzbina porudzbina;

    @ManyToOne
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvod proizvod;
    
    public StavkaPorudzbine() {
    	
    }

	public StavkaPorudzbine(Long id, int kolicina, double cena, Porudzbina porudzbina, Proizvod proizvod) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.cena = cena;
		this.porudzbina = porudzbina;
		this.proizvod = proizvod;
	}

	public StavkaPorudzbine(int kolicina, double cena, Porudzbina porudzbina, Proizvod proizvod) {
		super();
		this.kolicina = kolicina;
		this.cena = cena;
		this.porudzbina = porudzbina;
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

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
}
