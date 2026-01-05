import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app'; // Pazi: ovde mora biti './app' jer su rute u app.ts
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), // Ova linija povezuje tvoj app.ts sa aplikacijom
    provideHttpClient()
  ]
};