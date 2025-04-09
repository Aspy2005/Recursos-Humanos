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

  constructor(private fb: FormBuilder, private empleadoService: EmpleadoService) {
    this.empleadoForm = this.fb.group({
      cedula: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      direccion: ['', Validators.required],
      fechaIngreso: ['', Validators.required],
      edad: ['', Validators.required],
      cargo: ['', Validators.required],
      turno_trabajo: ['', Validators.required],
      residencia: ['', Validators.required],
      salario: ['', Validators.required]
    });
  }

  ordenCampos: string[] = [
    'cedula',
    'nombre',
    'apellido',
    'correo',
    'telefono',
    'direccion',
    'fechaIngreso',
    'edad',
    'cargo',
    'turno_trabajo',
    'residencia',
    'salario'
  ];
  
  
  registrar() {
    if (this.empleadoForm.valid) {
      const empleado: Empleado = this.empleadoForm.value;
      this.empleadoService.registrarEmpleado(empleado).subscribe({
        next: (res) => {
          alert('Empleado registrado exitosamente');
          this.empleadoForm.reset();
        },
        error: (err) => {
          console.error('Error al registrar empleado', err);
        }
      });
    }
  }
}
