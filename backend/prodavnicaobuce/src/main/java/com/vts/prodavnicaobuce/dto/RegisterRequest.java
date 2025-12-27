package com.vts.prodavnicaobuce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

	@NotBlank(message = "Ime je obavezno")
    @Size(min = 2, max = 50, message = "Ime mora imati između 2 i 50 karaktera")
    public String ime;

    @NotBlank(message = "Prezime je obavezno")
    @Size(min = 2, max = 50, message = "Prezime mora imati između 2 i 50 karaktera")
    public String prezime;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Email nije validan")
    public String email;

    @NotBlank(message = "Korisničko ime je obavezno")
    @Size(min = 4, max = 30, message = "Korisničko ime mora imati između 4 i 30 karaktera")
    public String username;

    @NotBlank(message = "Lozinka je obavezna")
    @Size(min = 8, message = "Lozinka mora imati najmanje 8 karaktera")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
        message = "Lozinka mora sadržati veliko slovo, malo slovo i broj"
    )
    public String password;

    @NotBlank(message = "Adresa je obavezna")
    @Size(min = 5, max = 100, message = "Adresa mora imati između 5 i 100 karaktera")
    public String adresa;

    @NotBlank(message = "Broj telefona je obavezan")
    public String brojTelefona;
}
