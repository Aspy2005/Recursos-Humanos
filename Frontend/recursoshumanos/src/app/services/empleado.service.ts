import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Empleado } from '../models/empleado.model';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators'; // Agregar catchError desde rxjs
import { RegistroEmpleadoResponse } from '../interfaces/registro-empleado-response';


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://localhost:8080'; // Punto base del backend

  constructor(private http: HttpClient) {}

  registrarEmpleado(empleado: Empleado): Observable<RegistroEmpleadoResponse> {  // Cambiado a RegistroEmpleadoResponse
    return this.http.post<RegistroEmpleadoResponse>(`${this.apiUrl}/empleado/registrar`, empleado);
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

  obtenerEmpleadoPorCedula(cedula: string): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.apiUrl}/empleado/findByCedula/${cedula}`);
  }
  
  

  // Actualizar los datos del empleado
  actualizarEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.put<Empleado>(`${this.apiUrl}/empleado/actualizar`, empleado);
  }

  eliminarEmpleado(cedula: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${cedula}`);
  }
}
