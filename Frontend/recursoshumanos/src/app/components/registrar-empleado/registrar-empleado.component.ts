import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmpleadoService } from '../../services/empleado.service';
import { Empleado } from '../../models/empleado.model';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-registrar-empleado',
  templateUrl: './registrar-empleado.component.html',
  styleUrl: './registrar-empleado.component.css',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule]
})
export class RegistrarEmpleadoComponent {
  empleadoForm: FormGroup;

  ordenCampos: string[] = [
    'cedulaEmpleado',
    'nombre',
    'apellido',
    'correo',
    'telefono',
    'direccion',
    'fechaIngreso',
    'edad',
    'cargo',
    'turnoTrabajo',
    'ciudad',
    'salario'
  ];

  etiquetas: { [key: string]: string } = {
    cedulaEmpleado: 'Cédula',
    nombre: 'Nombre',
    apellido: 'Apellido',
    correo: 'Correo electrónico',
    telefono: 'Teléfono',
    direccion: 'Dirección',
    fechaIngreso: 'Fecha de ingreso',
    edad: 'Edad',
    cargo: 'Cargo',
    turnoTrabajo: 'Turno de trabajo',
    ciudad: 'Ciudad de residencia',
    salario: 'Salario'
  };

  turnos: string[] = ['Mañana', 'Tarde', 'Noche', 'Rotativo'];

  constructor(private fb: FormBuilder, private empleadoService: EmpleadoService) {
    this.empleadoForm = this.fb.group({
      cedulaEmpleado: ['', [Validators.required, Validators.pattern(/^\d+$/)]], // Solo números
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{7,15}$/)]], // Solo números, 7 a 15 dígitos
      direccion: ['', Validators.required],
      fechaIngreso: ['', Validators.required],
      edad: ['', [Validators.required, Validators.min(18), Validators.max(65)]],
      cargo: ['', Validators.required],
      turnoTrabajo: ['', Validators.required],
      ciudad: ['', Validators.required],
      salario: ['', [Validators.required, Validators.min(0)]] // No negativo
    });
    
  }

  registrar() {
    if (this.empleadoForm.valid) {
      const empleado: Empleado = this.empleadoForm.value;
      this.empleadoService.registrarEmpleado(empleado).subscribe({
        next: () => {
          alert('Empleado registrado exitosamente');
          this.empleadoForm.reset();
        },
        error: (err) => {
          console.error('Error al registrar empleado', err);
        }
      });
    }
  }

  getControl(campo: string) {
    return this.empleadoForm.get(campo);
  }
}
