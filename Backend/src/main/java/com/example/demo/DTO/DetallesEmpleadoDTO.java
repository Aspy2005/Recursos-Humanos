package com.example.demo.DTO;

import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.PQR;
import java.util.List;

public class DetallesEmpleadoDTO {
    private Empleado empleado;
    private List<Asistencia> asistencia;
    private List<PQR> pqr;

    // Constructor
    public DetallesEmpleadoDTO(Empleado empleado, List<Asistencia> asistencia, List<PQR> pqr) {
        this.empleado = empleado;
        this.asistencia = asistencia;
        this.pqr = pqr;
    }

    // Getters y Setters
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Asistencia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<Asistencia> asistencia) {
        this.asistencia = asistencia;
    }

    public List<PQR> getPqr() {
        return pqr;
    }

    public void setPqr(List<PQR> pqr) {
        this.pqr = pqr;
    }
}
