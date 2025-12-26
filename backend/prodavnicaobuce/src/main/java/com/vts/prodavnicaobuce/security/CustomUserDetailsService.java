package com.vts.prodavnicaobuce.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.repository.KorisnikRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;

    public CustomUserDetailsService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Korisnik korisnik = korisnikRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Korisnik ne postoji"));

        if (korisnik.isBlokiran()) {
            throw new DisabledException("Korisnik je blokiran");
        }

        return new CustomUserDetails(korisnik);
    }
}
