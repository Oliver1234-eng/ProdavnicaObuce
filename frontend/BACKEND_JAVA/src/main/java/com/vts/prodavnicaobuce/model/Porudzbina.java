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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "kupac_id")
    private Korisnik kupac;

    @ManyToOne
    @JoinColumn(name = "prodavac_id")
    private Korisnik prodavac;
    
    @Column
    private String neregIme;

    @Column
    private String neregPrezime;

    @Column
    private String neregAdresa;
    
    @Column
    private String neregEmail;
    
    @Column
    private String neregTelefon;

    @OneToMany(
        mappedBy = "porudzbina",
        cascade = CascadeType.ALL
    )
    private List<StavkaPorudzbine> stavke;
    
    public Porudzbina() {
    	
    }

	public Porudzbina(Long id, LocalDateTime datumPorudzbine, StatusPorudzbine status, double ukupnaCena,
			Korisnik kupac, Korisnik prodavac, String neregIme, String neregPrezime, String neregAdresa,
			String neregEmail, String neregTelefon, List<StavkaPorudzbine> stavke) {
		super();
		this.id = id;
		this.datumPorudzbine = datumPorudzbine;
		this.status = status;
		this.ukupnaCena = ukupnaCena;
		this.kupac = kupac;
		this.prodavac = prodavac;
		this.neregIme = neregIme;
		this.neregPrezime = neregPrezime;
		this.neregAdresa = neregAdresa;
		this.neregEmail = neregEmail;
		this.neregTelefon = neregTelefon;
		this.stavke = stavke;
	}

	public Porudzbina(LocalDateTime datumPorudzbine, StatusPorudzbine status, double ukupnaCena, Korisnik kupac,
			Korisnik prodavac, String neregIme, String neregPrezime, String neregAdresa, String neregEmail,
			String neregTelefon, List<StavkaPorudzbine> stavke) {
		super();
		this.datumPorudzbine = datumPorudzbine;
		this.status = status;
		this.ukupnaCena = ukupnaCena;
		this.kupac = kupac;
		this.prodavac = prodavac;
		this.neregIme = neregIme;
		this.neregPrezime = neregPrezime;
		this.neregAdresa = neregAdresa;
		this.neregEmail = neregEmail;
		this.neregTelefon = neregTelefon;
		this.stavke = stavke;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumPorudzbine() {
		return datumPorudzbine;
	}

	public void setDatumPorudzbine(LocalDateTime datumPorudzbine) {
		this.datumPorudzbine = datumPorudzbine;
	}

	public StatusPorudzbine getStatus() {
		return status;
	}

	public void setStatus(StatusPorudzbine status) {
		this.status = status;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public Korisnik getProdavac() {
		return prodavac;
	}

	public void setProdavac(Korisnik prodavac) {
		this.prodavac = prodavac;
	}

	public String getNeregIme() {
		return neregIme;
	}

	public void setNeregIme(String neregIme) {
		this.neregIme = neregIme;
	}

	public String getNeregPrezime() {
		return neregPrezime;
	}

	public void setNeregPrezime(String neregPrezime) {
		this.neregPrezime = neregPrezime;
	}

	public String getNeregAdresa() {
		return neregAdresa;
	}

	public void setNeregAdresa(String neregAdresa) {
		this.neregAdresa = neregAdresa;
	}

	public String getNeregEmail() {
		return neregEmail;
	}

	public void setNeregEmail(String neregEmail) {
		this.neregEmail = neregEmail;
	}

	public String getNeregTelefon() {
		return neregTelefon;
	}

	public void setNeregTelefon(String neregTelefon) {
		this.neregTelefon = neregTelefon;
	}

	public List<StavkaPorudzbine> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaPorudzbine> stavke) {
		this.stavke = stavke;
	}
}
