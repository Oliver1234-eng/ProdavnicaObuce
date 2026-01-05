import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { KorpaService } from '../../services/korpa';

// Neophodno da Angular prepozna Bootstrap JS komande
declare var bootstrap: any;

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './pocetna.html',
  styleUrl: './pocetna.css'
})
export class PocetnaComponent implements OnInit { 
  brojacKorpe: number = 0;
  
  // Varijable za Lightbox/Modal
  stavkeUKorpi: any[] = [];
  ukupnaCena: number = 0;

  // --- KLJUČNA LINIJA KOJA TI NEDOSTAJE ---
  username: string | null = '';

  constructor(
    private router: Router,
    private korpaService: KorpaService,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    // Čitamo ime korisnika iz localStorage-a
    this.username = localStorage.getItem('username');

    // Pratimo promene u korpi
    this.korpaService.brojac$.subscribe(n => {
      this.brojacKorpe = n;
      this.cdr.detectChanges(); 
    });
  }

  // Funkcija koja otvara modal korpe
  otvoriKorpu() {
    this.osveziKorpu();
    const modalElement = document.getElementById('korpaModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  osveziKorpu() {
    const artikliIzServisa = this.korpaService.getArtikli();
    const slika1 = 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500';
    const slika2 = 'https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=500';
    const slika3 = 'https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=500';

    this.stavkeUKorpi = artikliIzServisa.map((s, index) => {
      let sl = slika3; 
      if (index % 3 === 0) sl = slika1;
      else if (index % 3 === 1) sl = slika2;
      return { ...s, slika: s.slika || sl };
    });

    this.ukupnaCena = this.stavkeUKorpi.reduce((acc, obj) => acc + Number(obj.cena), 0);
    this.cdr.detectChanges();
  }

  ukloniArtikal(index: number) {
    this.korpaService.obrisiIzKorpe(index);
    this.osveziKorpu(); 
  }

  zavrsiKupovinu() {
    alert('Kupovina je uspešno izvršena! Hvala Vam na poverenju.');
    this.korpaService.isprazniKorpu();
    this.osveziKorpu(); 
    
    const modalElement = document.getElementById('korpaModal');
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) modal.hide();
    }
  }

  odjaviSe() {
    console.log('Odjava sa početne stranice...');
    localStorage.removeItem('token'); 
    localStorage.removeItem('userRole');
    localStorage.removeItem('username');
    this.username = null;
    this.router.navigate(['/login']); 
  }
}