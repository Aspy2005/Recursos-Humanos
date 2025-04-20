import { EmpleadoService } from './../../services/empleado.service';
import { Empleado } from '../../models/empleado.model';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-actualizar-datos',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './actualizar-datos.component.html',
  styleUrls: ['./actualizar-datos.component.css']
})
export class ActualizarDatosComponent implements OnInit {
  empleado: Empleado = {
    cedulaEmpleado: '',
    nombre: '',
    apellido: '',
    telefono: '',
    direccion: '',
    fechaIngreso: '',
    edad: 0,
    cargo: '',
    turnoTrabajo: '',
    correo: '',
    ciudad: '',
    salario: 0
  };

  constructor(private AuthService: AuthService, private EmpleadoService: EmpleadoService) { }

  ngOnInit(): void {
    // Obtener la cédula del usuario logueado desde el AuthService
    const cedula = this.AuthService.getCedula();

    if (cedula) {
      // Solicitar los datos del empleado desde el backend usando la cédula
      this.EmpleadoService.obtenerEmpleadoPorCedula(cedula).subscribe((empleadoData) => {
        this.empleado = empleadoData; // Llenar el formulario con los datos del empleado
      });
    }
  }
  actualizado: boolean = false;

actualizarEmpleado() {
  this.EmpleadoService.actualizarEmpleado(this.empleado).subscribe({
    next: () => {
      this.actualizado = true;

      // Opcional: ocultar el mensaje después de 5 segundos
      setTimeout(() => {
        this.actualizado = false;
      }, 5000);
    },
    error: (err) => {
      console.error('Error al actualizar', err);
      this.actualizado = false;
    }
  });
}

}
