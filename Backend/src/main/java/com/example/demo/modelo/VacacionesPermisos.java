package com.example.demo.modelo;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class VacacionesPermisos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permiso")
	@SequenceGenerator(name = "seq_permiso", sequenceName = "seq_permiso", allocationSize = 1)
	@Column(name = "id_permiso")
	private Integer idPermiso;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

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

}
