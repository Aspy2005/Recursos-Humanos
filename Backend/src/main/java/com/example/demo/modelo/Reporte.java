package com.example.demo.modelo;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reporte")
    @SequenceGenerator(name = "seq_reporte", sequenceName = "seq_reporte", allocationSize = 1)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cedula_personal", referencedColumnName = "cedula_personal")
    private PersonalRH personalRH;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    @Column(name = "ruta", length = 255)
    private String ruta;

    

    public Reporte() {
		super();
	}

	public Reporte(Integer idReporte, Empleado empleado, PersonalRH personalRH, String tipo, LocalDate fechaGeneracion,
			String ruta) {
		super();
		this.idReporte = idReporte;
		this.empleado = empleado;
		this.personalRH = personalRH;
		this.tipo = tipo;
		this.fechaGeneracion = fechaGeneracion;
		this.ruta = ruta;
	}

	public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
