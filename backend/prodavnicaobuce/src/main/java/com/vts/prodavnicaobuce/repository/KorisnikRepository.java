package com.vts.prodavnicaobuce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vts.prodavnicaobuce.model.Korisnik;
import com.vts.prodavnicaobuce.model.UlogaKorisnika;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Optional<Korisnik> findByUsername(String username);

    Optional<Korisnik> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
    List<Korisnik> findByUlogaKorisnikaIn(List<UlogaKorisnika> uloge);
}
