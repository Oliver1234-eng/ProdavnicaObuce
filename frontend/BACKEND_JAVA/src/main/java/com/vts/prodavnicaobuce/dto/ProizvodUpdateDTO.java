package com.vts.prodavnicaobuce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProizvodUpdateDTO {

    private String naziv; // Dodato

    @NotNull(message = "Cena je obavezna")
    @Min(value = 1, message = "Cena mora biti veća od 0")
    private Double cena;

    @NotNull(message = "Količina je obavezna")
    @Min(value = 0, message = "Količina ne može biti negativna")
    private Integer kolicinaNaStanju;

    private String slika; // Dodato

    public ProizvodUpdateDTO() {}

    // Getteri i Setteri za sve (uključujući naziv i sliku)
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public Double getCena() { return cena; }
    public void setCena(Double cena) { this.cena = cena; }
    public Integer getKolicinaNaStanju() { return kolicinaNaStanju; }
    public void setKolicinaNaStanju(Integer kolicinaNaStanju) { this.kolicinaNaStanju = kolicinaNaStanju; }
    public String getSlika() { return slika; }
    public void setSlika(String slika) { this.slika = slika; }
}