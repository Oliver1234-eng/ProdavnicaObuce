package com.vts.prodavnicaobuce.dto;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.UlogaKorisnika;

public class KorisnikDTO {

	private Long id;
	
    private String username;
    
    private UlogaKorisnika uloga;
    
    private boolean blokiran;

    public static KorisnikDTO fromEntity(Korisnik korisnik) {
        KorisnikDTO dto = new KorisnikDTO();
        dto.setId(korisnik.getId());
        dto.setUsername(korisnik.getUsername());
        dto.setUloga(korisnik.getUlogaKorisnika());
        dto.setBlokiran(korisnik.isBlokiran());
        
        return dto;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}
}
