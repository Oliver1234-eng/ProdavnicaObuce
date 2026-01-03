package com.vts.prodavnicaobuce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PorudzbinaDTO {

	private Long id;
	
    private LocalDateTime datumPorudzbine;
    
    private String status;
    
    private double ukupnaCena;

    private String kupacUsername;
    
    private String prodavacUsername;

    private String neregIme;
    
    private String neregPrezime;
    
    private String neregAdresa;
    
    private String neregEmail;
    
    private String neregTelefon;

    private List<StavkaPorudzbineDTO> stavke;
    
    public PorudzbinaDTO() {
    	
    }

	public PorudzbinaDTO(Long id, LocalDateTime datumPorudzbine, String status, double ukupnaCena, String kupacUsername,
			String prodavacUsername, String neregIme, String neregPrezime, String neregAdresa, String neregEmail,
			String neregTelefon, List<StavkaPorudzbineDTO> stavke) {
		super();
		this.id = id;
		this.datumPorudzbine = datumPorudzbine;
		this.status = status;
		this.ukupnaCena = ukupnaCena;
		this.kupacUsername = kupacUsername;
		this.prodavacUsername = prodavacUsername;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getKupacUsername() {
		return kupacUsername;
	}

	public void setKupacUsername(String kupacUsername) {
		this.kupacUsername = kupacUsername;
	}

	public String getProdavacUsername() {
		return prodavacUsername;
	}

	public void setProdavacUsername(String prodavacUsername) {
		this.prodavacUsername = prodavacUsername;
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

	public List<StavkaPorudzbineDTO> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaPorudzbineDTO> stavke) {
		this.stavke = stavke;
	}
}
