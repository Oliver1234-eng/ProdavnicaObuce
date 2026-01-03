import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { ProizvodService } from '../../services/proizvod';
import { Proizvod } from '../../models/proizvod';
import { KorpaService } from '../../services/korpa';

declare var bootstrap: any;

@Component({
  selector: 'app-proizvodi',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './proizvodi.html',
  styleUrl: './proizvodi.css'
})
export class ProizvodiComponent implements OnInit {
  listaProizvoda: Proizvod[] = [];
  brojacKorpe: number = 0;
  stavkeUKorpi: Proizvod[] = [];
  ukupnaCena: number = 0;
  isProdavac: boolean = false;
  
  // DODATO: Promenljiva za prikaz korisničkog imena
  username: string | null = '';

  // VARIJABLE ZA UPLOAD SLIKE
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  // OBJEKAT ZA DODAVANJE/IZMENU
  trenutniProizvod: Proizvod = this.resetujProizvod();
  naslovModala: string = 'DODAJ NOVI PROIZVOD';

  private readonly SLIKA1 = 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500';
  private readonly SLIKA2 = 'https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=500';
  private readonly SLIKA3 = 'https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=500';

  constructor(
    private proizvodService: ProizvodService,
    private korpaService: KorpaService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    const uloga = localStorage.getItem('userRole');
    this.isProdavac = (uloga === 'PRODAVAC');
    
    // DODATO: Uzimanje korisničkog imena iz localStorage-a
    this.username = localStorage.getItem('username');

    this.ucitajSveProizvode();

    this.korpaService.brojac$.subscribe(n => {
      this.brojacKorpe = n;
      this.cdr.detectChanges();
    });
  }

  resetujProizvod(): Proizvod {
    return {
      naziv: '',
      cena: 0,
      velicina: '',
      kategorija: 'PATIKE',
      kolicinaNaStanju: 0,
      slika: ''
    };
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  otvoriModalZaDodavanje() {
    this.naslovModala = 'DODAJ NOVI PROIZVOD';
    this.trenutniProizvod = this.resetujProizvod();
    this.selectedFile = null;
    this.imagePreview = null;
    const modalElement = document.getElementById('proizvodModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  izmeniProizvod(p: Proizvod) {
    this.naslovModala = 'IZMENI PROIZVOD';
    this.trenutniProizvod = { ...p };
    this.selectedFile = null;
    this.imagePreview = null;
    const modalElement = document.getElementById('proizvodModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  sacuvajProizvod() {
    if (this.selectedFile) {
      this.proizvodService.uploadSlike(this.selectedFile).subscribe({
        next: (putanja: string) => {
          this.trenutniProizvod.slika = putanja;
          this.izvrsiSnimanjePodataka();
        },
        error: (err: any) => alert('Greška pri prebacivanju slike na server!')
      });
    } else {
      this.izvrsiSnimanjePodataka();
    }
  }

  private izvrsiSnimanjePodataka() {
    if (this.trenutniProizvod.id) {
      this.proizvodService.azurirajProizvod(this.trenutniProizvod).subscribe({
        next: () => {
          alert('Proizvod uspešno ažuriran!');
          this.zatvoriModalIZatvori();
        },
        error: (err: any) => {
          if (err.status === 200) {
            alert('Proizvod uspešno ažuriran!');
            this.zatvoriModalIZatvori();
          } else {
            console.error('Greška pri izmeni:', err);
          }
        }
      });
    } else {
      this.proizvodService.dodajProizvod(this.trenutniProizvod).subscribe({
        next: () => {
          alert('Novi proizvod uspešno dodat!');
          this.zatvoriModalIZatvori();
        },
        error: (err: any) => {
          if (err.status === 200) {
            alert('Novi proizvod uspešno dodat!');
            this.zatvoriModalIZatvori();
          } else {
            console.error('Greška pri dodavanju:', err);
          }
        }
      });
    }
  }

  zatvoriModalIZatvori() {
    this.selectedFile = null;
    this.imagePreview = null;
    this.ucitajSveProizvode();
    const modalElement = document.getElementById('proizvodModal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    if (modal) modal.hide();
  }

  obrisiProizvod(id: number | undefined): void {
    if (!id) return;
    if (confirm('Da li ste sigurni da želite da obrišete ovaj proizvod?')) {
      this.proizvodService.obrisiProizvod(id).subscribe({
        next: (poruka) => {
          alert(poruka);
          this.ucitajSveProizvode();
        },
        error: (err: any) => {
          if (err.status === 200) {
            alert('Proizvod obrisan!');
            this.ucitajSveProizvode();
          } else {
            console.error('Greška pri brisanju:', err);
          }
        }
      });
    }
  }

  otvoriKorpu(): void {
    this.osveziKorpu();
    const modalElement = document.getElementById('korpaModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  osveziKorpu(): void {
    const artikliIzServisa = this.korpaService.getArtikli();
    this.stavkeUKorpi = artikliIzServisa.map((s, index) => {
      if (s.slika && s.slika.trim() !== '') {
        return { ...s };
      }
      let sl = this.SLIKA3;
      if (index % 3 === 0) sl = this.SLIKA1;
      else if (index % 3 === 1) sl = this.SLIKA2;
      return { ...s, slika: sl };
    });
    this.ukupnaCena = this.stavkeUKorpi.reduce((acc, obj) => acc + Number(obj.cena), 0);
    this.cdr.detectChanges();
  }

  ukloniArtikal(index: number): void {
    this.korpaService.obrisiIzKorpe(index);
    this.osveziKorpu();
  }

  zavrsiKupovinu(): void {
    alert('Kupovina je uspešno izvršena!');
    this.korpaService.isprazniKorpu();
    this.osveziKorpu();
    const modalElement = document.getElementById('korpaModal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    if (modal) modal.hide();
  }

  dodajUKorpu(proizvod: Proizvod): void { 
    this.korpaService.dodajUCorpu(proizvod); 
  }

  ucitajSveProizvode(): void {
    this.proizvodService.getProizvodi().subscribe({
      next: (data) => {
        this.listaProizvoda = data.map((p, index) => {
          if (!p.slika || p.slika.trim() === '') {
            let odabranaSlika = this.SLIKA3;
            if (index % 3 === 0) odabranaSlika = this.SLIKA1;
            else if (index % 3 === 1) odabranaSlika = this.SLIKA2;
            return { ...p, slika: odabranaSlika };
          }
          return p; 
        });
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Greška pri učitavanju:', err)
    });
  }

  odjaviSe(): void {
    localStorage.removeItem('token'); 
    localStorage.removeItem('userRole');
    localStorage.removeItem('username'); // Brišemo i ime pri odjavi
    this.router.navigate(['/login']);
  }
}