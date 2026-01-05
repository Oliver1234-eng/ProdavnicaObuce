import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  credentials = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    console.log('Pokušaj prijave sa:', this.credentials);
    
    this.authService.login(this.credentials).subscribe({
      next: (response: any) => {
        console.log('Server odgovorio:', response);

        // 1. Čuvamo token
        if (response && response.token) {
          localStorage.setItem('token', response.token);
        }

        // 2. Čuvamo ulogu korisnika
        if (response && response.uloga) {
          localStorage.setItem('userRole', response.uloga);
          console.log('Uloga uspešno sačuvana:', response.uloga);
        } else {
          console.warn('Backend nije poslao polje uloga.');
        }

        // 3. Čuvamo korisničko ime da bi se videlo u meniju
        localStorage.setItem('username', this.credentials.username);
        console.log('Korisničko ime sačuvano:', this.credentials.username);
        
        // 4. Prebacujemo korisnika na proizvode
        this.router.navigate(['/pocetna']);
      },
      error: (err) => {
        console.error('Greška pri prijavi:', err);
        alert('Neispravno korisničko ime ili lozinka!');
      }
    });
  }
}