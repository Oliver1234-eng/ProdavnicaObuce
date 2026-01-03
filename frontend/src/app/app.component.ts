import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // Ostavi samo RouterOutlet
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule], // Izbacio sam RouterLink ovde
  templateUrl: './app.component.html'
})
export class AppComponent {
  // Klasa je sada prazna jer nam ne treba logika za gornji meni
}