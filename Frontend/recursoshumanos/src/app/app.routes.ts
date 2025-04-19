import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarEmpleadoComponent } from './components/registrar-empleado/registrar-empleado.component';
import { ReporteEvaluacionComponent } from './components/reporte-evaluacion/reporte-evaluacion.component';
import { PermisosComponent } from './components/vacaciones-permisos/vacaciones-permisos.component';
import { LoginComponent } from './components/login/login.component';
import { MenuEmpleadoComponent } from './menu-empleado/menu-empleado.component';
import { MenuRhComponent } from './menu-rh/menu-rh.component';
import { RoleGuard } from './guards/role.guard';  // Importa el guard
import { ActualizarDatosComponent } from './components/actualizar-datos/actualizar-datos.component';
import { GestorEmpleadoComponent } from './components/gestor-empleado/gestor-empleado.component';  // Importa el componente gestor de empleados

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'reporte-evaluacion', component: ReporteEvaluacionComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'vacaciones-permisos', component: PermisosComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  
  // Ruta para el gestor de empleados (solo acceso personalRH)
  { path: 'gestor-empleado', component: GestorEmpleadoComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  
  // Rutas para los menús
  { path: 'menu-empleado', component: MenuEmpleadoComponent, canActivate: [RoleGuard], data: { roles: ['Empleado'] } },
  { path: 'menu-rh', component: MenuRhComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  
  // Ruta para el componente de actualizar datos
  { path: 'actualizar-datos', component: ActualizarDatosComponent },
  
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
