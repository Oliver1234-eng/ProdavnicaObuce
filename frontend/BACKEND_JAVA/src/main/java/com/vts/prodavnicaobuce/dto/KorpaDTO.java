package com.vts.prodavnicaobuce.dto;

import java.util.List;

public class KorpaDTO {

	private String sessionId;
	
    private List<StavkaKorpeDTO> stavke;
    
    private double ukupnaCena;
    
    public KorpaDTO() {
    	
    }

	public KorpaDTO(String sessionId, List<StavkaKorpeDTO> stavke, double ukupnaCena) {
		super();
		this.sessionId = sessionId;
		this.stavke = stavke;
		this.ukupnaCena = ukupnaCena;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<StavkaKorpeDTO> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaKorpeDTO> stavke) {
		this.stavke = stavke;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
}
