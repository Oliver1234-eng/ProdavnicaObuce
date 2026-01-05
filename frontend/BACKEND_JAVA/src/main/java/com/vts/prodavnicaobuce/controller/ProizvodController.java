package com.vts.prodavnicaobuce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.vts.prodavnicaobuce.dto.ProizvodDTO;
import com.vts.prodavnicaobuce.model.Kategorija;
import com.vts.prodavnicaobuce.model.Proizvod;
import com.vts.prodavnicaobuce.repository.KategorijaRepository;
import com.vts.prodavnicaobuce.service.ProizvodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proizvodi")
@CrossOrigin(origins = "http://localhost:4200")
public class ProizvodController {

    private final ProizvodService proizvodService;
    private final KategorijaRepository kategorijaRepository;
    
    // Putanja gde će se slike čuvati na tvom kompjuteru
    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public ProizvodController(ProizvodService proizvodService, KategorijaRepository kategorijaRepository) {
        this.proizvodService = proizvodService;
        this.kategorijaRepository = kategorijaRepository;
    }

    @GetMapping
    public List<ProizvodDTO> sviProizvodi() {
        return proizvodService.sviAktivni()
                .stream()
                .map(ProizvodDTO::fromEntity)
                .toList();
    }
    
    // NOVA METODA ZA UPLOAD SLIKE SA RAČUNARA
    @PostMapping("/upload")
    public ResponseEntity<String> uploadSlike(@RequestParam("file") MultipartFile file) {
        try {
            // Kreiraj folder ako ne postoji
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generiši unikatno ime fajla da se ne preklapaju
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            
            // Sačuvaj fajl na disk
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Vrati putanju koju će Angular koristiti (npr. http://localhost:8080/uploads/ime.jpg)
            return ResponseEntity.ok("/uploads/" + fileName);
            
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Greška pri uploadu: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> dodajProizvod(@RequestBody @Valid ProizvodDTO request) {
        Kategorija kategorija = kategorijaRepository.findByNaziv(request.getKategorija())
                .orElseGet(() -> {
                    Kategorija nova = new Kategorija();
                    nova.setNaziv(request.getKategorija());
                    return kategorijaRepository.save(nova);
                });

        Proizvod proizvod = new Proizvod();
        proizvod.setNaziv(request.getNaziv());
        proizvod.setCena(request.getCena());
        proizvod.setVelicina(request.getVelicina());
        proizvod.setKolicinaNaStanju(request.getKolicinaNaStanju());
        proizvod.setSlika(request.getSlika());
        proizvod.setKategorija(kategorija);
        proizvod.setObrisan(false);

        proizvodService.save(proizvod);
        return ResponseEntity.ok("Proizvod uspešno dodat!");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> izmeniProizvod(@PathVariable Long id, @RequestBody @Valid ProizvodDTO dto) {
        proizvodService.azurirajKompletanProizvod(id, dto);
        return ResponseEntity.ok("Proizvod uspešno ažuriran");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> obrisi(@PathVariable Long id) {
        proizvodService.obrisiProizvod(id);
        return ResponseEntity.ok("Proizvod uspešno obrisan");
    }
}