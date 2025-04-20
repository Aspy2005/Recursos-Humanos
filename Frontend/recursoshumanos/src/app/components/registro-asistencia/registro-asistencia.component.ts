import { AuthService } from './../../services/auth.service';
import { AsistenciaService } from './../../services/asistencia.service';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-registro-asistencia',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './registro-asistencia.component.html',
  styleUrls: ['./registro-asistencia.component.css']
})
export class RegistroAsistenciaComponent {
  mensaje: string = '';

  constructor(
    private AsistenciaService: AsistenciaService,
    private AuthService: AuthService
  ) {}

  registrarEntrada() {
    const cedula = this.AuthService.getCedula();
  
    this.AsistenciaService.registrarEntrada(cedula).subscribe({
      next: res => {
        if (res.includes("Hora de entrada registrada")) {
          alert("✅ " + res);
        } else if (res.includes("La hora de entrada ya fue registrada")) {
          alert("⚠️ " + res);
        }
      },
      error: err => {
        alert("❌ " + this.mensaje);
      }
    });
  }

  registrarSalida() {
    const cedula = this.AuthService.getCedula();
  
    this.AsistenciaService.registrarSalida(cedula).subscribe({
      next: res => {
        if (res.includes("Hora de salida registrada")) {
          alert("✅ Éxito: " + res);
        }
      },
      error: err => {
        const errorMsg = err.error || "Ocurrió un error inesperado al registrar la salida.";
        if (errorMsg.includes("No se puede registrar la salida sin haber registrado la entrada")) {
          alert("❌ Error: " + errorMsg);
        } else {
          alert("⚠️ " + errorMsg);
        }
      }
    });
  }

  borrarEntrada() {
    const cedula = this.AuthService.getCedula();
  
    this.AsistenciaService.borrarEntrada(cedula).subscribe({
      next: (respuesta) => {
        this.mensaje = respuesta.message; // <- extrae el campo message del objeto
        alert(this.mensaje);
      },
      error: (err) => {
        console.error('Error al borrar entrada:', err);
        // Mostrar mensaje del backend si viene como texto plano o JSON
        if (err.error?.message) {
          this.mensaje = err.error.message;
        } else if (typeof err.error === 'string') {
          this.mensaje = err.error;
        } else {
          this.mensaje = 'Error inesperado al borrar entrada.';
        }
        alert(this.mensaje);
      }
    });
  }
  
  

  
}
