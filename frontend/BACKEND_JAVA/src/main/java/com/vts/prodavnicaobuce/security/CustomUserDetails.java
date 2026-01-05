package com.vts.prodavnicaobuce.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vts.prodavnicaobuce.model.Korisnik;

public class CustomUserDetails implements UserDetails {

    private final Korisnik korisnik;

    public CustomUserDetails(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority(
                "ROLE_" + korisnik.getUlogaKorisnika().name()
            )
        );
    }

    @Override
    public String getPassword() {
        return korisnik.getPassword();
    }

    @Override
    public String getUsername() {
        return korisnik.getUsername();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !korisnik.isBlokiran();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    
    @Override public boolean isCredentialsNonExpired() { return true; }
}
