package com.vts.prodavnicaobuce.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @OneToMany(mappedBy = "kategorija")
    private List<Proizvod> proizvodi;
    
    public Kategorija() {
    	
    }

	public Kategorija(Long id, String naziv, List<Proizvod> proizvodi) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.proizvodi = proizvodi;
	}

	public Kategorija(String naziv, List<Proizvod> proizvodi) {
		super();
		this.naziv = naziv;
		this.proizvodi = proizvodi;
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

	public List<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(List<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
}
