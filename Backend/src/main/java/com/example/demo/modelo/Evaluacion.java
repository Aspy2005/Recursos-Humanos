package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evaluacion")
    @SequenceGenerator(name = "seq_evaluacion", sequenceName = "seq_evaluacion", allocationSize = 1)
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cedula_personal", referencedColumnName = "cedula_personal")
    private PersonalRH personalRH;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "puntaje_desempeno")
    private Double puntajeDesempeno;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    
    public Evaluacion() {
		super();
	}


	public Evaluacion(Integer idEvaluacion, Empleado empleado, PersonalRH personalRH, LocalDate fecha,
			Double puntajeDesempeno, String observaciones) {
		super();
		this.idEvaluacion = idEvaluacion;
		this.empleado = empleado;
		this.personalRH = personalRH;
		this.fecha = fecha;
		this.puntajeDesempeno = puntajeDesempeno;
		this.observaciones = observaciones;
	}


    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public PersonalRH getPersonalRH() {
        return personalRH;
    }

    public void setPersonalRH(PersonalRH personalRH) {
        this.personalRH = personalRH;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getPuntajeDesempeno() {
        return puntajeDesempeno;
    }

    public void setPuntajeDesempeno(Double puntajeDesempeno) {
        this.puntajeDesempeno = puntajeDesempeno;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
