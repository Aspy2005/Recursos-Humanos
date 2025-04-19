import { AuthService } from './../services/auth.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private AuthService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    
    // Obtén el rol del usuario desde el servicio de autenticación
    const userRole = this.AuthService.getUserRole();
    
    // Comprobar si el rol es adecuado para acceder a la ruta
    if (next.data['roles'] && next.data['roles'].indexOf(userRole) === -1) {
      // Si el rol no tiene acceso, redirigir a una página de error o login
      this.router.navigate(['/login']);
      return false;
    }
    
    // Si el rol es válido, permitir el acceso
    return true;
  }
}
