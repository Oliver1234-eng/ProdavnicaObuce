package com.vts.prodavnicaobuce.dto;

import com.vts.prodavnicaobuce.model.Proizvod;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProizvodDTO {

	@NotBlank(message = "Naziv proizvoda je obavezan")
    private String naziv;

    @NotNull(message = "Cena je obavezna")
    @Min(value = 0, message = "Cena mora biti veća ili jednaka 0")
    private Double cena;

    @NotNull(message = "Veličina je obavezna")
    @Min(value = 30, message = "Veličina mora biti veća ili jednaka 30") // primer
    private Integer velicina;

    @NotNull(message = "Količina na stanju je obavezna")
    @Min(value = 0, message = "Količina mora biti veća ili jednaka 0")
    private Integer kolicinaNaStanju;

    @NotNull(message = "Kategorija je obavezna")
    private String kategorija;
    
    public ProizvodDTO() {
    	
    }

    public ProizvodDTO(Proizvod proizvod) {
        this.naziv = proizvod.getNaziv();
        this.cena = proizvod.getCena();
        this.velicina = proizvod.getVelicina();
        this.kolicinaNaStanju = proizvod.getKolicinaNaStanju();
        this.kategorija = proizvod.getKategorija() != null ? proizvod.getKategorija().getNaziv() : null;
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

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
}
