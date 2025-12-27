package com.vts.prodavnicaobuce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Korpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String sessionId;

    @OneToOne
    @JoinColumn(name = "kupac_id")
    private Korisnik kupac;

    @OneToMany(
        mappedBy = "korpa",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<StavkaKorpe> stavke;
    
    public Korpa() {
    	
    }

	public Korpa(Long id, String sessionId, Korisnik kupac, List<StavkaKorpe> stavke) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.kupac = kupac;
		this.stavke = stavke;
	}

	public Korpa(String sessionId, Korisnik kupac, List<StavkaKorpe> stavke) {
		super();
		this.sessionId = sessionId;
		this.kupac = kupac;
		this.stavke = stavke;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public List<StavkaKorpe> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaKorpe> stavke) {
		this.stavke = stavke;
	}
}
