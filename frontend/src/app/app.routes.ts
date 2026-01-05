import { Routes } from '@angular/router';
import { ProizvodiComponent } from './components/proizvodi/proizvodi';
import { LoginComponent } from './components/login/login';
import { ONamaComponent } from './components/o-nama/o-nama';
import { PocetnaComponent } from './components/pocetna/pocetna';
import { RegistracijaComponent } from './components/registracija/registracija';

export const routes: Routes = [
  // Redosled je ključan!
  { path: 'login', component: LoginComponent },
  { path: 'registracija', component: RegistracijaComponent },
  { path: 'pocetna', component: PocetnaComponent },
  { path: 'proizvodi', component: ProizvodiComponent },
  { path: 'o-nama', component: ONamaComponent },
  
  // Šta se dešava kad se tek uđe na sajt
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  
  // Hvatač grešaka (mora biti poslednji)
  { path: '**', redirectTo: '/login' }
];