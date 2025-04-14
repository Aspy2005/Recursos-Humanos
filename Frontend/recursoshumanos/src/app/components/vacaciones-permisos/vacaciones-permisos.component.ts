import { VacacionesPermisosService } from './../../services/vacaciones-permisos.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-vacaciones-permisos',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule],
  templateUrl: './vacaciones-permisos.component.html',
  styleUrl: './vacaciones-permisos.component.css'
})
export class PermisosComponent implements OnInit {
  permisos: any[] = [];

  constructor(private VacacionesPermisosService: VacacionesPermisosService) {}

  ngOnInit(): void {
    this.cargarPermisos();
  }

  cargarPermisos() {
    this.VacacionesPermisosService.obtenerPermisos().subscribe(data => {
      // ✅ Filtrar solo los que están en estado "Pendiente"
      this.permisos = data.filter(p => p.estado?.toLowerCase() === 'pendiente');
    });
  }

  cambiarEstado(id: number, estado: string) {
    this.VacacionesPermisosService.cambiarEstado(id, estado).subscribe(() => {
      this.cargarPermisos();
    });
  }
}