package com.example.demo.modelo;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Asistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_asistencia")
	@SequenceGenerator(name = "seq_asistencia", sequenceName = "seq_asistencia", allocationSize = 1)
	@Column(name = "id_asistencia")
	private Integer idAsistencia;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "horas_trabajadas")
    private Double horasTrabajadas;

}
