package com.vts.prodavnicaobuce.dto;

public class StavkaKorpeDTO {

	private Long proizvodId;
	
    private String nazivProizvoda;
    
    private int kolicina;
    
    private double cena;
    
    public StavkaKorpeDTO() {
    	
    }

	public StavkaKorpeDTO(Long proizvodId, String nazivProizvoda, int kolicina, double cena) {
		super();
		this.proizvodId = proizvodId;
		this.nazivProizvoda = nazivProizvoda;
		this.kolicina = kolicina;
		this.cena = cena;
	}

	public Long getProizvodId() {
		return proizvodId;
	}

	public void setProizvodId(Long proizvodId) {
		this.proizvodId = proizvodId;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
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
}
