import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-solicitud-vacacion-permiso',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule, FormsModule, RouterModule],
  templateUrl: './solicitud-vacacion-permiso.component.html',
  styleUrls: ['./solicitud-vacacion-permiso.component.css']
})
export class SolicitudVacacionPermisoComponent {
  solicitudForm: FormGroup;
  mensaje: string = '';
  error: string = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.solicitudForm = this.fb.group({
      tipo: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required],
      motivo: ['', Validators.required],
      observaciones: ['']
    });
  }

  enviarSolicitud() {
    this.mensaje = '';
    this.error = '';

    const { fechaInicio, fechaFin } = this.solicitudForm.value;

    if (new Date(fechaFin) < new Date(fechaInicio)) {
      this.error = 'La fecha de fin no puede ser anterior a la fecha de inicio.';
      return;
    }

    const solicitud = {
      ...this.solicitudForm.value,
      cedulaEmpleado: localStorage.getItem('cedula')
    };

    this.http.post('http://localhost:8080/vacaciones-permisos/crear', solicitud)
      .subscribe({
        next: (res: any) => {
          this.mensaje = res.mensaje || 'Solicitud enviada con éxito, pendiente de aprobación.';
          this.solicitudForm.reset();
        },
        error: (err) => {
          this.error = 'Ocurrió un error al enviar la solicitud.';
          console.log(err)
        }
      });
  }
}
