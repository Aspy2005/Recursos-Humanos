package com.example.demo.modelo;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class PQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pqr")
    private Integer idPqr;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "fecha")
    private LocalDate fecha;


    public PQR() {
		super();
	}

	public PQR(Integer idPqr, Empleado empleado, String tipo, String descripcion, LocalDate fecha) {
		super();
		this.idPqr = idPqr;
		this.empleado = empleado;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Integer getIdPqr() {
        return idPqr;
    }

    public void setIdPqr(Integer idPqr) {
        this.idPqr = idPqr;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
