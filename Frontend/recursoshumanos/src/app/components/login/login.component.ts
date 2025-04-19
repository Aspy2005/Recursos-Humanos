import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  cedula = '';
  contrasena = '';
  error = '';

  constructor(private http: HttpClient, private router: Router, private AuthService: AuthService) {}

loading = false;

login() {
  this.error = '';
  this.loading = true;

  this.http.post<any>('http://localhost:8080/usuario/login', {
    cedula: this.cedula,
    contrasena: this.contrasena
  }).subscribe({
    next: res => {
      console.log('Respuesta del backend:', res); // Para ver qué está llegando

      // Usar el AuthService para guardar el rol y la cédula
      this.AuthService.setUserRole(res.rol, res.cedula);

      // Redirigir según el rol
      if (res.rol === 'Empleado') {
        this.router.navigate(['/menu-empleado']);
      } else if (res.rol === 'personalRH') {
        this.router.navigate(['/menu-rh']);
      }
    },
    error: () => {
      this.error = 'Cédula o contraseña incorrectas.';
    },
    complete: () => {
      this.loading = false;
    }
  });
}
  
}
