package com.vts.prodavnicaobuce.dto;

public class LoginResponse {
    private String token;
    private String uloga; // Ovo je ključno polje koje Angular čeka

    // Konstruktor za uspeh (kada se korisnik prijavi)
    public LoginResponse(String token, String uloga) {
        this.token = token;
        this.uloga = uloga;
    }

    // Konstruktor za grešku (ako login ne uspe)
    public LoginResponse(String token) {
        this.token = token;
    }

    // Getteri i Setteri su OBAVEZNI da bi Spring mogao da pretvori ovo u JSON
    public String getToken() { 
        return token; 
    }
    
    public void setToken(String token) { 
        this.token = token; 
    }

    public String getUloga() { 
        return uloga; 
    }

    public void setUloga(String uloga) { 
        this.uloga = uloga; 
    }
}