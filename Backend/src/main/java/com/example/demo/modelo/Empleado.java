package com.example.demo.modelo;
import java.time.LocalDate;

import jakarta.persistence.*;

	@Entity
	public class Empleado {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empleado")
		@SequenceGenerator(name = "seq_empleado", sequenceName = "seq_empleado", allocationSize = 1)
		@Column(name = "id_empleado")
		private Integer idEmpleado;


	    @Column(name = "nombre", length = 50)
	    private String nombre;

	    @Column(name = "apellido", length = 50)
	    private String apellido;

	    @Column(name = "cedula", length = 20)
	    private String cedula;

	    @Column(name = "correo", length = 100)
	    private String correo;

	    @Column(name = "telefono", length = 20)
	    private String telefono;

	    @Column(name = "direccion", length = 100)
	    private String direccion;

	    @Column(name = "fecha_ingreso")
	    private LocalDate fechaIngreso;

	}


