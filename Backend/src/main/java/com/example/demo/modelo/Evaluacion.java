package com.example.demo.modelo;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Evaluacion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evaluacion")
	@SequenceGenerator(name = "seq_evaluacion", sequenceName = "seq_evaluacion", allocationSize = 1)
	@Column(name = "id_evaluacion")
	private Integer idEvaluacion;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "puntaje_desempeno")
    private Double puntajeDesempeno;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

}
