import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registracija',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './registracija.html',
  styleUrl: './registracija.css'
})
export class RegistracijaComponent {
  
  // Objekat koji šalje PRODAVAC ulogu na backend
  kupacData = {
    ime: '',
    prezime: '',
    email: '',
    adresa: '',
    brojTelefona: '',
    username: '',
    password: '',
    uloga: 'PRODAVAC' 
  };

  constructor(private authService: AuthService, private router: Router) {}

  onRegistracija() {
    console.log('Slanje podataka na registraciju:', this.kupacData);
    
    this.authService.register(this.kupacData).subscribe({
      next: (response: any) => {
        console.log('Server kaže:', response);
        alert('Uspešna registracija PRODAVCA! Sada se možete ulogovati.');
        this.router.navigate(['/login']);
      },
      error: (err: any) => {
        console.error('Kompletna greška sa servera:', err);

        // 1. Provera za validaciju (400 Bad Request) - npr. kratak username
        if (err.status === 400) {
          alert('Greška u podacima: Korisničko ime mora imati bar 4 karaktera, a lozinka bar 8 karaktera (veliko slovo, malo slovo i broj).');
        } 
        // 2. Provera ako su username/email već zauzeti (500 Internal Server Error)
        else if (err.status === 500) {
          alert('Greška: Korisničko ime ili email su već zauzeti.');
        } 
        // 3. Ostale tekstualne greške
        else if (typeof err.error === 'string' && err.error.length > 0) {
          alert('Greška: ' + err.error);
        } 
        // 4. Opšta greška
        else {
          alert('Registracija nije uspela. Proverite da li je backend pokrenut.');
        }
      }
    });
  }
}