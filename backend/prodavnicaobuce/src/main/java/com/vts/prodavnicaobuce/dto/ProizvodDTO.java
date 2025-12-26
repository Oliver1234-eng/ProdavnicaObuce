package com.vts.prodavnicaobuce.dto;

import com.vts.prodavnicaobuce.model.Proizvod;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProizvodDTO {

	private Long id;
	
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
    	this.id = proizvod.getId();
        this.naziv = proizvod.getNaziv();
        this.cena = proizvod.getCena();
        this.velicina = proizvod.getVelicina();
        this.kolicinaNaStanju = proizvod.getKolicinaNaStanju();
        this.kategorija = proizvod.getKategorija() != null ? proizvod.getKategorija().getNaziv() : null;
    }
    
    public static ProizvodDTO fromEntity(Proizvod proizvod) {
        ProizvodDTO dto = new ProizvodDTO();
        dto.setId(proizvod.getId());
        dto.setNaziv(proizvod.getNaziv());
        dto.setCena(proizvod.getCena());
        dto.setVelicina(proizvod.getVelicina());
        dto.setKolicinaNaStanju(proizvod.getKolicinaNaStanju());
        dto.setKategorija(proizvod.getKategorija().getNaziv());
        
        return dto;
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

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
}
