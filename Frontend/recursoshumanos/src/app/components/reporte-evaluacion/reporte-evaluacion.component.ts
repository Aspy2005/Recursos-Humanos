import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpleadoService } from './../../services/empleado.service';
import { ReporteEvaluacionService } from './../../services/reporte-evaluacion.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-reporte-evaluacion',
  standalone: true,
  templateUrl: './reporte-evaluacion.component.html',
  styleUrls: ['./reporte-evaluacion.component.css'],
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule, RouterModule]
})
export class ReporteEvaluacionComponent implements OnInit {
  evaluacionForm!: FormGroup;
  empleadosConRegistros: any[] = [];
  asistencia: any[] = [];
  pqr: any[] = [];
  empleadoSeleccionado: any = null;
  today: Date = new Date();

  constructor(
    private fb: FormBuilder,
    private EmpleadoService: EmpleadoService,
    private ReporteEvaluacionService: ReporteEvaluacionService
  ) {}

  ngOnInit(): void {
    this.evaluacionForm = this.fb.group({
      cedula: ['', Validators.required],
      asistencia: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      pqr: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      observaciones: ['']
    });

    this.EmpleadoService.obtenerEmpleadosConRegistros().subscribe(
      (empleados) => {
        console.log('Empleados obtenidos:', empleados);
        this.empleadosConRegistros = empleados;
      },
      (error) => {
        console.error('Error al cargar empleados:', error);
      }
    );
  }

  seleccionarEmpleado(empleado: any): void {
    console.log('Empleado seleccionado:', empleado);
    this.empleadoSeleccionado = empleado;
    this.evaluacionForm.patchValue({ cedula: empleado.cedulaEmpleado });

    this.ReporteEvaluacionService.obtenerDetallesEmpleado(empleado.cedulaEmpleado).subscribe(
      (data) => {
        console.log('Detalles del empleado:', data);
        this.asistencia = data.asistencia;
        this.pqr = data.pqr;
      },
      (error) => {
        console.error('Error al obtener detalles del empleado:', error);
        this.asistencia = [];
        this.pqr = [];
      }
    );
  }

  enviarEvaluacion(): void {
    const cedula = this.evaluacionForm.value.cedula;
  
    const asistencia = this.evaluacionForm.value.asistencia;
    const pqr = this.evaluacionForm.value.pqr;
  
    const evaluacion = {
      puntajeDesempeno: ((asistencia + pqr) / 2).toFixed(2), // promedio
      observaciones: this.evaluacionForm.value.observaciones,
      fecha: this.today.toISOString().split('T')[0] // yyyy-MM-dd
    };
  
    this.ReporteEvaluacionService.registrarEvaluacion(cedula, evaluacion).subscribe({
      next: () => alert('Evaluación registrada correctamente'),
      error: (err) => {
        console.error('Error al registrar evaluación:', err);
        alert('Error al registrar la evaluación');
      }
    });
  }
  
}
