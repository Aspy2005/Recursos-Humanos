import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ReporteRequest {
  idEmpleado: string;
  tipo: string;
  cedulaRH: string;
}

export interface Reporte {
  idReporte: number;
  tipo: string;
  fechaGeneracion: string;
  ruta: string;
  empleado: any;
  personalRH: any;
}

@Injectable({
  providedIn: 'root',
})
export class ReporteService {
  private apiUrl = 'http://localhost:8080/reportes';

  constructor(private http: HttpClient) {}

  generarReporte(data: ReporteRequest): Observable<Reporte> {
    return this.http.post<Reporte>(`${this.apiUrl}/generar`, data);
  }

  descargarReporte(idReporte: number): void {
    const url = `${this.apiUrl}/descargar/${idReporte}`;
    window.open(url, '_blank');
  }
}
