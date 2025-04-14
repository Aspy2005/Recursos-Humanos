import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Empleado } from '../models/empleado.model';
import { Observable } from 'rxjs';

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
}
