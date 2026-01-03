import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Putanja prati @RequestMapping("/auth") iz tvoje Jave
  private apiUrl = 'http://localhost:8080/auth'; 

  constructor(private http: HttpClient) { }

  // Prijava: šalje na /auth/login
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  // Registracija: Šalje na /auth/register
  // DODATO: { responseType: 'text' } jer Java vraća običan String, a ne JSON
  register(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData, { responseType: 'text' });
  }
}