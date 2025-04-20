import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';  // Importa throwError desde 'rxjs'
import { catchError } from 'rxjs/operators';  // Solo importa catchError


export interface Asistencia {
  idAsistencia?: number;
  fecha?: string;
  horaEntrada?: string;
  horaSalida?: string;
  horasTrabajadas?: number;
  empleadoCedula?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AsistenciaService {

  private apiUrl = 'http://localhost:8080/asistencia'; // ajusta la URL si es necesario

  constructor(private http: HttpClient) { }

  registrarEntrada(cedula: string): Observable<string> {
    return this.http.post(`${this.apiUrl}/entrada/${cedula}`, null, { responseType: 'text' });
  }

  registrarSalida(cedula: string): Observable<string> {
    return this.http.post(`${this.apiUrl}/salida/${cedula}`, null, { responseType: 'text' });
  }

  // Método para borrar la entrada del día
  borrarEntrada(cedula: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/borrarEntrada`, { cedula })
      .pipe(
        catchError(this.handleError)
      );
  }

  // Método para manejar los errores
  private handleError(error: any) {
    console.error('Ha ocurrido un error:', error);
    // Usamos throwError en lugar de Observable.throw
    return throwError(error);  // Esto manejará el error correctamente
  }
}
