import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarEmpleadoComponent } from './components/registrar-empleado/registrar-empleado.component';
import { ReporteEvaluacionComponent } from './components/reporte-evaluacion/reporte-evaluacion.component';
import { PermisosComponent } from './components/vacaciones-permisos/vacaciones-permisos.component';

export const routes: Routes = [
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
  { path: 'reporte-evaluacion', component: ReporteEvaluacionComponent },
  { path: 'vacaciones-permisos', component: PermisosComponent }, // 👈 nueva ruta
  { path: '', redirectTo: 'registrar-empleado', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
