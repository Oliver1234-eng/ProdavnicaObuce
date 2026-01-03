import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { PocetnaComponent } from './components/pocetna/pocetna';
import { RegistracijaComponent } from './components/registracija/registracija';
import { ProizvodiComponent } from './components/proizvodi/proizvodi';
import { ONamaComponent } from './components/o-nama/o-nama';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'pocetna', component: PocetnaComponent }, 
  { path: 'registracija', component: RegistracijaComponent },
  { path: 'proizvodi', component: ProizvodiComponent },
  { path: 'o-nama', component: ONamaComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];