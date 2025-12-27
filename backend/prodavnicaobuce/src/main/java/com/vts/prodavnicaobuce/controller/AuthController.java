package com.vts.prodavnicaobuce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vts.prodavnicaobuce.dto.LoginRequest;
import com.vts.prodavnicaobuce.dto.LoginResponse;
import com.vts.prodavnicaobuce.dto.RegisterRequest;
import com.vts.prodavnicaobuce.security.CustomUserDetailsService;
import com.vts.prodavnicaobuce.security.jwt.JwtService;
import com.vts.prodavnicaobuce.service.KorisnikService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final KorisnikService korisnikService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(KorisnikService korisnikService, AuthenticationManager authenticationManager,
			JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
		super();
		this.korisnikService = korisnikService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.customUserDetailsService = customUserDetailsService;
	}

    // Endpoint za registraciju korisnika
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        korisnikService.registerKupac(request);
        return ResponseEntity.ok("Kupac uspešno registrovan");
    }

	// Endpoint za login korisnika
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(request.username, request.password)
	        );

	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.username);

	        String token = jwtService.generateToken(userDetails);

	        return ResponseEntity.ok(new LoginResponse(token));

	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new LoginResponse("Pogrešan username ili password"));
	    }
	}
}
