import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Proizvod } from '../models/proizvod';

@Injectable({
  providedIn: 'root'
})
export class ProizvodService {
  private apiUrl = 'http://localhost:8080/proizvodi';

  constructor(private http: HttpClient) { }

  getProizvodi(): Observable<Proizvod[]> {
    return this.http.get<Proizvod[]>(this.apiUrl).pipe(
      tap(data => console.log('Svi proizvodi:', data))
    );
  }

  // NOVA METODA ZA UPLOAD SLIKE SA RAČUNARA
  uploadSlike(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);
    // Šaljemo na /upload rutu i očekujemo putanju do slike kao običan tekst
    return this.http.post(`${this.apiUrl}/upload`, formData, { responseType: 'text' }).pipe(
      tap(res => console.log('Putanja uploadovane slike:', res))
    );
  }

  // DODATE METODE ZA PRODAVCA SA ISPRAVKOM ZA TEXT ODGOVOR
  dodajProizvod(proizvod: Proizvod): Observable<string> {
    return this.http.post(this.apiUrl, proizvod, { responseType: 'text' }).pipe(
      tap(res => console.log('Server kaže:', res))
    );
  }

  azurirajProizvod(proizvod: Proizvod): Observable<string> {
    return this.http.put(`${this.apiUrl}/${proizvod.id}`, proizvod, { responseType: 'text' }).pipe(
      tap(res => console.log('Server kaže:', res))
    );
  }

  obrisiProizvod(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' }).pipe(
      tap(res => console.log('Server kaže:', res))
    );
  }
}