import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Proizvod } from '../models/proizvod';

@Injectable({
  providedIn: 'root'
})
export class KorpaService {
  private artikli: Proizvod[] = [];
  
  // BehaviorSubject koji prati broj stavki
  private brojac = new BehaviorSubject<number>(0);
  brojac$ = this.brojac.asObservable();

  constructor() {
    // Čim se servis pokrene, pokušaj da učitaš stare podatke iz memorije browsera
    const sacuvanaKorpa = localStorage.getItem('korpa');
    if (sacuvanaKorpa) {
      this.artikli = JSON.parse(sacuvanaKorpa);
      this.brojac.next(this.artikli.length);
    }
  }

  // Dodavanje proizvoda
  dodajUCorpu(proizvod: Proizvod) {
    // 1. Dodaj u niz u memoriji
    this.artikli.push(proizvod);
    
    // 2. Snimi celo novo stanje u localStorage
    localStorage.setItem('korpa', JSON.stringify(this.artikli));
    
    // 3. Javi svima da je broj porastao
    this.brojac.next(this.artikli.length);
    
    console.log('Proizvod dodat. Trenutno u korpi:', this.artikli);
  }

  // Vraća listu artikala
  getArtikli() {
    return this.artikli;
  }

  // Brisanje jednog artikla
  obrisiIzKorpe(index: number) {
    // 1. Izbriši iz niza u memoriji
    this.artikli.splice(index, 1);
    
    // 2. Osveži localStorage sa novim nizom
    localStorage.setItem('korpa', JSON.stringify(this.artikli));
    
    // 3. Javi svima novi broj
    this.brojac.next(this.artikli.length);
  }

  // Potpuno pražnjenje (Završetak kupovine)
  isprazniKorpu() {
    // 1. ISPRAVKA: Čistimo niz u memoriji (ovo ti je falilo)
    this.artikli = [];
    
    // 2. Brišemo iz browsera
    localStorage.removeItem('korpa');
    
    // 3. Javi svima da je korpa sada 0
    this.brojac.next(0);
    
    console.log('Korpa je ispražnjena.');
  }

  getBrojArtikala() {
    return this.artikli.length;
  }
}