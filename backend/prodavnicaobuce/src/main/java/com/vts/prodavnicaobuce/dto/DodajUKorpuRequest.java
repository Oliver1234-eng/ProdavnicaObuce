package com.vts.prodavnicaobuce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DodajUKorpuRequest {

	@NotNull
    private Long proizvodId;

    @Min(1)
    private int kolicina;

    public DodajUKorpuRequest() {
    	
    }

    public DodajUKorpuRequest(@NotNull Long proizvodId, @Min(1) int kolicina) {
		super();
		this.proizvodId = proizvodId;
		this.kolicina = kolicina;
	}

	public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
