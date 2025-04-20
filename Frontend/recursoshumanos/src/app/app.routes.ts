import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarEmpleadoComponent } from './components/registrar-empleado/registrar-empleado.component';
import { ReporteEvaluacionComponent } from './components/reporte-evaluacion/reporte-evaluacion.component';
import { VacacionesPermisosComponent } from './components/vacaciones-permisos/vacaciones-permisos.component';
import { LoginComponent } from './components/login/login.component';
import { MenuEmpleadoComponent } from './components/menu-empleado/menu-empleado.component';
import { MenuRhComponent } from './components/menu-rh/menu-rh.component';
import { RoleGuard } from './guards/role.guard';
import { ActualizarDatosComponent } from './components/actualizar-datos/actualizar-datos.component';
import { GestorEmpleadoComponent } from './components/gestor-empleado/gestor-empleado.component';
import { RegistroAsistenciaComponent } from './components/registro-asistencia/registro-asistencia.component';

// Importa tu nuevo componente standalone
import { SolicitudVacacionPermisoComponent } from './components/solicitud-vacacion-permiso/solicitud-vacacion-permiso.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'reporte-evaluacion', component: ReporteEvaluacionComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'vacaciones-permisos', component: VacacionesPermisosComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'gestor-empleado', component: GestorEmpleadoComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'menu-empleado', component: MenuEmpleadoComponent},
  { path: 'menu-rh', component: MenuRhComponent, canActivate: [RoleGuard], data: { roles: ['personalRH'] } },
  { path: 'actualizar-datos', component: ActualizarDatosComponent },
  { path: 'registrar-horas-trabajadas', component: RegistroAsistenciaComponent},
  
  // ✅ Ruta accesible por todos para solicitar vacaciones o permisos
  { path: 'solicitar-vacacion-permiso', component: SolicitudVacacionPermisoComponent },

  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }