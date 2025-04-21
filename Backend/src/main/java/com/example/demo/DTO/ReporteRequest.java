package com.example.demo.DTO;

public class ReporteRequest {
    private String idEmpleado;
    private String tipo; // Puede ser "Asistencia", "Desempeño", "Permiso"
    private String cedulaRH;

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCedulaRH() {
        return cedulaRH;
    }

    public void setCedulaRH(String cedulaRH) {
        this.cedulaRH = cedulaRH;
    }
}
