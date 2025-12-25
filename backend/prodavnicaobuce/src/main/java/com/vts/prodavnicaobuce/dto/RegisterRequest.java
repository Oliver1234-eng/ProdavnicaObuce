package com.vts.prodavnicaobuce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

	@NotBlank
    public String ime;

    @NotBlank
    public String prezime;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @NotBlank
    public String adresa;

    @NotBlank
    public String brojTelefona;
}
