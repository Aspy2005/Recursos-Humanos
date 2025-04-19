import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private rol: string = '';
  private cedula: string = '';

  constructor() { }

  // Método para guardar el rol y la cédula
  setUserRole(rol: string, cedula: string): void {
    localStorage.setItem('rol', rol);
    localStorage.setItem('cedula', cedula);
  }

  // Métodos para obtener los valores almacenados
  getUserRole(): string {
    return localStorage.getItem('rol') || '';
  }

  getCedula(): string {
    return localStorage.getItem('cedula') || '';
  }

  // Método para verificar si el usuario está autenticado
  isAuthenticated(): boolean {
    return !!localStorage.getItem('rol') && !!localStorage.getItem('cedula');
  }

  // Método para cerrar sesión
  logout(): void {
    localStorage.removeItem('rol');
    localStorage.removeItem('cedula');
  }
}
