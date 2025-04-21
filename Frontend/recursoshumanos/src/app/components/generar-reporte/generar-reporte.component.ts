import { ReporteService, ReporteRequest } from './../../services/reporte.service';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AuthService } from './../../services/auth.service';

@Component({
  selector: 'app-generar-reporte',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './generar-reporte.component.html',
  styleUrls: ['./generar-reporte.component.css']
})
export class GenerarReporteComponent {
  data: ReporteRequest = {
    idEmpleado: '',
    tipo: 'asistencia',
    cedulaRH: ''
  };

  reporte: any = null;

  constructor(
    private ReporteService: ReporteService,
    private route: ActivatedRoute,
    private AuthService: AuthService
  ) {}

  ngOnInit(): void {
    // Accedemos a la cédula RH desde el AuthService
    this.data.cedulaRH = this.AuthService.getCedula();
  }

  generar() {
    this.ReporteService.generarReporte(this.data).subscribe({
      next: (res) => {
        this.reporte = res;
      },
      error: (err) => {
        alert('Error al generar el reporte: ' + err.error);
      }
    });
  }

  descargar() {
    if (this.reporte && this.reporte.idReporte) {
      this.ReporteService.descargarReporte(this.reporte.idReporte);
    }
  }
}
