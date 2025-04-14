package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "horas_trabajadas")
    private Double horasTrabajadas;

    
    
    public Asistencia() {
		super();
	}


	public Asistencia(Integer idAsistencia, Empleado empleado, LocalDate fecha, LocalTime horaEntrada,
			LocalTime horaSalida, Double horasTrabajadas) {
		super();
		this.idAsistencia = idAsistencia;
		this.empleado = empleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.horasTrabajadas = horasTrabajadas;
	}


    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
