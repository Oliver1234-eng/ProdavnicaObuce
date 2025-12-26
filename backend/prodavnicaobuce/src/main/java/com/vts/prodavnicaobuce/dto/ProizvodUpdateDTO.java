package com.vts.prodavnicaobuce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProizvodUpdateDTO {

	@NotNull(message = "Cena je obavezna")
    @Min(value = 1, message = "Cena mora biti veća od 0")
    private Double cena;

    @NotNull(message = "Količina je obavezna")
    @Min(value = 0, message = "Količina ne može biti negativna")
    private Integer dodatnaKolicina;

	public ProizvodUpdateDTO() {
		super();
	}

	public ProizvodUpdateDTO(
			@NotNull(message = "Cena je obavezna") @Min(value = 1, message = "Cena mora biti veća od 0") Double cena,
			@NotNull(message = "Količina je obavezna") @Min(value = 0, message = "Količina ne može biti negativna") Integer dodatnaKolicina) {
		super();
		this.cena = cena;
		this.dodatnaKolicina = dodatnaKolicina;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Integer getDodatnaKolicina() {
		return dodatnaKolicina;
	}

	public void setDodatnaKolicina(Integer dodatnaKolicina) {
		this.dodatnaKolicina = dodatnaKolicina;
	} 
}
