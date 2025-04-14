export interface Empleado {
    cedula: string;
    nombre: string;
  }
  
  export interface Evaluacion {
    id?: number;
    asistencia: number;
    pqr: number;
    observaciones: string;
    fecha?: string;
    empleado: Empleado;
  }
  