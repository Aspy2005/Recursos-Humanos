import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';  // Importa ReactiveFormsModule y validadores
import { CommonModule } from '@angular/common';
import { EmpleadoService } from './../../services/empleado.service';

@Component({
  selector: 'app-gestor-empleado',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],  // Asegúrate de importar ReactiveFormsModule
  templateUrl: './gestor-empleado.component.html',
  styleUrls: ['./gestor-empleado.component.css']
})
export class GestorEmpleadoComponent {
  cedulaBuscar: string = ''; // Variable para la cédula del empleado a buscar
  empleado: any = null; // Variable para almacenar los datos del empleado encontrado
  empleadoEncontrado = false; // Indica si el empleado fue encontrado
  noEncontrado = false; // Indica si el empleado no fue encontrado
  mensaje = ''; // Mensaje para mostrar en la UI

  // Definir el FormGroup con validaciones
  empleadoForm: FormGroup = new FormGroup({
    cedulaBuscar: new FormControl('', [
      Validators.required, 
      Validators.pattern('^[0-9]+$')  // Aceptar solo números
    ]),
    nombre: new FormControl('', [Validators.required]),
    apellido: new FormControl('', [Validators.required]),
    correo: new FormControl('', [Validators.required, Validators.email]),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{7,15}$')]),  // Solo números de 7 a 15 dígitos
    direccion: new FormControl(''),
    ciudad: new FormControl(''),
    fechaIngreso: new FormControl(''),
    edad: new FormControl('', [Validators.required, Validators.min(18), Validators.max(65)]),  // Edad entre 18 y 65
    cargo: new FormControl(''),
    turnoTrabajo: new FormControl(''),
    salario: new FormControl('', [Validators.required, Validators.min(0)]),  // Salario no puede ser negativo
  });
  

  constructor(private http: HttpClient, private empleadoService: EmpleadoService) {}

  // Buscar empleado por cédula
  // Buscar empleado por cédula
buscarEmpleado() {
  const cedula = this.empleadoForm.get('cedulaBuscar')?.value;  // Obtener el valor de la cédula desde el formulario
  this.http.get<any>(`http://localhost:8080/empleado/findByCedula/${cedula}`).subscribe({
    next: data => {
      this.empleado = data; // Asigna los datos del empleado a la variable
      this.empleadoEncontrado = true;
      this.noEncontrado = false;
      this.mensaje = '';  // Resetea el mensaje
      // Poblamos el formulario con los datos del empleado
      this.empleadoForm.patchValue(this.empleado);
    },
    error: (err) => {
      if (err.status === 404) {
        // Si el error es 404, significa que no se encontró el empleado
        this.empleadoEncontrado = false;
        this.noEncontrado = true;
        this.mensaje = 'Empleado no encontrado';
      } else {
        // Si el error es otro, muestra un mensaje general de error
        this.empleadoEncontrado = false;
        this.noEncontrado = false;
        this.mensaje = 'Error al buscar empleado';
      }
    }
  });
}


  // Actualizar empleado
  actualizarEmpleado() {
    if (this.empleadoForm.valid) {
      this.http.put(`http://localhost:8080/empleado/actualizar/${this.empleado.cedulaEmpleado}`, this.empleadoForm.value).subscribe({
        next: () => {
          this.mensaje = 'Información actualizada con éxito';
        },
        error: () => {
          this.mensaje = 'Error al actualizar empleado';
        }
      });
    } else {
      this.mensaje = 'Por favor, complete todos los campos correctamente';
    }
  }

  // Eliminar empleado
  eliminarEmpleado() {
    if (confirm('¿Estás seguro de eliminar este empleado?')) {
      this.http.delete(`http://localhost:8080/empleado/eliminar/${this.empleado.cedulaEmpleado}`).subscribe({
        next: () => {
          this.mensaje = 'Empleado eliminado con éxito';
          this.empleado = null; // Limpiar los datos del empleado
          this.empleadoEncontrado = false; // Resetear el estado de búsqueda
        },
        error: () => {
          this.mensaje = 'Error al eliminar empleado';
        }
      });
    }
  }

  // Métodos de acceso a los controles para mostrar errores
  getControl(campo: string) {
    return this.empleadoForm.get(campo);
  }
}
