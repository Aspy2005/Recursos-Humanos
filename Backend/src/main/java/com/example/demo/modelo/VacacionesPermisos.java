package com.example.demo.modelo;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class VacacionesPermisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cedula_personal", referencedColumnName = "cedula_personal")
    private PersonalRH personalRH;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "dias_solicitados")
    private Integer diasSolicitados;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "estado", length = 20)
    private String estado;

    

    public VacacionesPermisos() {
		super();
	}

	public VacacionesPermisos(Integer idPermiso, Empleado empleado, PersonalRH personalRH, String tipo,
			Integer diasSolicitados, LocalDate fechaInicio, LocalDate fechaFin, String motivo, String observaciones,
			String estado) {
		super();
		this.idPermiso = idPermiso;
		this.empleado = empleado;
		this.personalRH = personalRH;
		this.tipo = tipo;
		this.diasSolicitados = diasSolicitados;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.motivo = motivo;
		this.observaciones = observaciones;
		this.estado = estado;
	}

	public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
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

    public Integer getDiasSolicitados() {
        return diasSolicitados;
    }

    public void setDiasSolicitados(Integer diasSolicitados) {
        this.diasSolicitados = diasSolicitados;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
