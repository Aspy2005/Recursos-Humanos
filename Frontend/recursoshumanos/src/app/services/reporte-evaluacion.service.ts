import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReporteEvaluacionService {
  private apiUrl = 'http://localhost:8080/evaluacion';

  constructor(private http: HttpClient) {}

  // Obtiene empleados que tienen registros (para que solo se muestren candidatos válidos)
  obtenerEmpleadosConRegistros(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/empleados-con-registros`);
  }

  // Obtiene detalles de un empleado: info personal, asistencia y PQR
  obtenerDetallesEmpleado(cedulaEmpleado: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/detalles/${cedulaEmpleado}`);
  }

  // Envía una nueva evaluación para el empleado
  registrarEvaluacion(cedulaEmpleado: string, evaluacion: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/registrar/${cedulaEmpleado}`, evaluacion);
  }
}
