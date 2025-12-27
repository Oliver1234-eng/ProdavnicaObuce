package com.vts.prodavnicaobuce.dto;

public class NaruciNeregRequest {

	private String ime;
	
    private String prezime;
    
    private String email;
    
    private String telefon;
    
    private String adresa;
    
    public NaruciNeregRequest() {
    	
    }

	public NaruciNeregRequest(String ime, String prezime, String email, String telefon, String adresa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
}
