import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Empleado } from '../models/empleado.model';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators'; // Agregar catchError desde rxjs


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://localhost:8080'; // Punto base del backend

  constructor(private http: HttpClient) {}

  registrarEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.post<Empleado>(`${this.apiUrl}/empleado/registrar`, empleado);
  }

  // 🚀 Obtener empleados con registros de asistencia o PQR
  obtenerEmpleadosConRegistros(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(`${this.apiUrl}/evaluacion/empleados-con-registros`);
  }

  validarCedula(cedula: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/empleado/validar-cedula/${cedula}`).pipe(
      catchError(error => {
        if (error.status === 409) { // Cédula ya existe
          return of(true); // Retorna true para indicar que la cédula existe
        }
        return of(false); // En caso de error distinto, retorna false
      })
    );
  }
  
  
}
