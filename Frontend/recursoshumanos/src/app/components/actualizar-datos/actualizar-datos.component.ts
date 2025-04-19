import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-actualizar-datos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './actualizar-datos.component.html',
  styleUrls: ['./actualizar-datos.component.css']
})
export class ActualizarDatosComponent implements OnInit {
  empleado: any = {};
  cedula = '123456'; // Puedes reemplazar esto con el valor que obtengas del login

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.obtenerDatosEmpleado();
  }

  obtenerDatosEmpleado() {
    this.http.get<any>(`http://localhost:8080/empleados/${this.cedula}`)
      .subscribe(data => this.empleado = data);
  }

  actualizarDatos() {
    const datosActualizados = {
      direccion: this.empleado.direccion,
      ciudad: this.empleado.ciudad,
      telefono: this.empleado.telefono,
      correo: this.empleado.correo
    };

    this.http.put(`http://localhost:8080/empleados/${this.cedula}`, datosActualizados)
      .subscribe(() => alert('Datos actualizados correctamente'));
  }
}
