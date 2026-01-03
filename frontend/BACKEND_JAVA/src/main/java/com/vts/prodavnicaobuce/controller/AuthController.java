package com.vts.prodavnicaobuce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.vts.prodavnicaobuce.dto.LoginRequest;
import com.vts.prodavnicaobuce.dto.LoginResponse;
import com.vts.prodavnicaobuce.dto.RegisterRequest;
import com.vts.prodavnicaobuce.security.CustomUserDetailsService;
import com.vts.prodavnicaobuce.security.jwt.JwtService;
import com.vts.prodavnicaobuce.service.KorisnikService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final KorisnikService korisnikService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(KorisnikService korisnikService, 
                          AuthenticationManager authenticationManager,
                          JwtService jwtService, 
                          CustomUserDetailsService customUserDetailsService) {
        this.korisnikService = korisnikService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        korisnikService.registerKupac(request);
        return ResponseEntity.ok("Korisnik uspešno registrovan");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            // 1. Provera kredencijala (Username i Password)
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username, request.password)
            );

            // 2. Učitavanje UserDetails-a iz tvoje baze
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.username);

            // 3. Generisanje JWT tokena preko tvog servisa
            String token = jwtService.generateToken(userDetails);

            // 4. VAĐENJE ULOGE IZ USERDETAILS
            // Uzimamo prvu dodeljenu ulogu i skidamo "ROLE_" prefiks ako postoji
            String uloga = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                    .orElse("KUPAC");

            // 5. SLANJE ODGOVORA (Token + Uloga)
            // Ovo sada radi jer smo u LoginResponse.java dodali novi konstruktor i polje 'uloga'
            return ResponseEntity.ok(new LoginResponse(token, uloga));

        } catch (BadCredentialsException e) {
            // Ako login ne uspe, vraćamo 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Pogrešan username ili password"));
        }
    }
}