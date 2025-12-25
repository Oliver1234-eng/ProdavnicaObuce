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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    public Korisnik() {
    	
    }

	public Korisnik(Long id, String ime, String prezime, String email, String username, String password, String adresa,
			String brojTelefona, boolean blokiran, UlogaKorisnika ulogaKorisnika, Korpa korpa,
			List<Porudzbina> porudzbine, List<Porudzbina> obradjenePorudzbine) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.blokiran = blokiran;
		this.ulogaKorisnika = ulogaKorisnika;
		this.korpa = korpa;
		this.porudzbine = porudzbine;
		this.obradjenePorudzbine = obradjenePorudzbine;
	}

	public Korisnik(String ime, String prezime, String email, String username, String password, String adresa,
			String brojTelefona, boolean blokiran, UlogaKorisnika ulogaKorisnika, Korpa korpa,
			List<Porudzbina> porudzbine, List<Porudzbina> obradjenePorudzbine) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.blokiran = blokiran;
		this.ulogaKorisnika = ulogaKorisnika;
		this.korpa = korpa;
		this.porudzbine = porudzbine;
		this.obradjenePorudzbine = obradjenePorudzbine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	public UlogaKorisnika getUlogaKorisnika() {
		return ulogaKorisnika;
	}

	public void setUlogaKorisnika(UlogaKorisnika ulogaKorisnika) {
		this.ulogaKorisnika = ulogaKorisnika;
	}

	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

	public List<Porudzbina> getObradjenePorudzbine() {
		return obradjenePorudzbine;
	}

	public void setObradjenePorudzbine(List<Porudzbina> obradjenePorudzbine) {
		this.obradjenePorudzbine = obradjenePorudzbine;
	}
}
