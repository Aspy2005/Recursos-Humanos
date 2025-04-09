import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarEmpleadoComponent } from './components/registrar-empleado/registrar-empleado.component';

export const routes: Routes = [
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
  { path: '', redirectTo: 'registrar-empleado', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
