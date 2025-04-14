import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VacacionesPermisosService {
  private baseUrl = 'http://localhost:8080/vacaciones-permisos';

  constructor(private http: HttpClient) {}

  obtenerPermisos(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  cambiarEstado(id: number, estado: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}/estado?estado=${estado}`, {});
  }
}
