import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';

declare var bootstrap: any;

@Component({
  selector: 'app-o-nama',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './o-nama.html',
  styleUrl: './o-nama.css'
})
export class ONamaComponent implements OnInit {
  username: string | null = '';
  stavkeUKorpi: any[] = [];
  ukupnaCena: number = 0;
  brojacKorpe: number = 0;

  constructor(private router: Router) {}

  ngOnInit() {
    // Proveravamo sve ključeve da bismo bili sigurni
    this.username = localStorage.getItem('user') || 
                    localStorage.getItem('username') || 
                    localStorage.getItem('korisnik');
    
    console.log('Ulogovani korisnik je:', this.username); // Ovo će ti ispisati u konzoli (F12) šta je našao
    this.osveziKorpu();
  }

  osveziKorpu() {
    const korpaString = localStorage.getItem('korpa');
    const korpa = JSON.parse(korpaString || '[]');
    this.stavkeUKorpi = korpa;
    this.brojacKorpe = korpa.length;
    this.ukupnaCena = korpa.reduce((sum: number, item: any) => sum + Number(item.cena), 0);
  }

  otvoriKorpu() {
    const modalElement = document.getElementById('korpaModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      this.osveziKorpu();
      modal.show();
    }
  }

  ukloniArtikal(index: number) {
    this.stavkeUKorpi.splice(index, 1);
    localStorage.setItem('korpa', JSON.stringify(this.stavkeUKorpi));
    this.osveziKorpu();
  }

  odjaviSe() {
    // Čistimo sve moguće ključeve pri odjavi
    localStorage.removeItem('user');
    localStorage.removeItem('username');
    localStorage.removeItem('korisnik');
    this.router.navigate(['/login']);
  }

  zavrsiKupovinu() {
    alert('Kupovina uspešno završena!');
    localStorage.removeItem('korpa');
    this.osveziKorpu();
    const modalElement = document.getElementById('korpaModal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    if (modal) modal.hide();
  }
}