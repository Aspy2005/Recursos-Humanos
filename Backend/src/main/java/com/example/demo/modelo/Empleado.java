package com.example.demo.modelo;
import java.time.LocalDate;

import jakarta.persistence.*;

	@Entity
	public class Empleado {


		@Id
	    @Column(name = "cedula", length = 20)
	    private String cedula;
	    
	    @Column(name = "nombre", length = 50)
	    private String nombre;

	    @Column(name = "apellido", length = 50)
	    private String apellido;


	    @Column(name = "correo", length = 100)
	    private String correo;

	    @Column(name = "telefono", length = 20)
	    private String telefono;

	    @Column(name = "direccion", length = 100)
	    private String direccion;

	    @Column(name = "fecha_ingreso")
	    private LocalDate fechaIngreso;
	    
	    @Column(name = "edad", length= 3)
	    private Integer edad;
	    
	    @Column(name = "cargo", length=20)
	    private String cargo;
	    
	    @Column(name= "turno_trabajo", length=20)
	    private String turno_trabajo;
	    
	    @Column(name= "residencia", length=20)
	    private String residencia;
	    
	    @Column(name = "salario")
	    private Double salario;


		public Empleado() {
			super();
		}

		public Empleado( String nombre, String apellido, String cedula, String correo,
				String telefono, String direccion, LocalDate fechaIngreso, Integer edad, String cargo,
				String turno_trabajo, String residencia, Double salario) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.cedula = cedula;
			this.correo = correo;
			this.telefono = telefono;
			this.direccion = direccion;
			this.fechaIngreso = fechaIngreso;
			this.edad = edad;
			this.cargo = cargo;
			this.turno_trabajo = turno_trabajo;
			this.residencia = residencia;
			this.salario = salario;
		}


		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public LocalDate getFechaIngreso() {
			return fechaIngreso;
		}

		public void setFechaIngreso(LocalDate fechaIngreso) {
			this.fechaIngreso = fechaIngreso;
		}

		public Integer getEdad() {
			return edad;
		}

		public void setEdad(Integer edad) {
			this.edad = edad;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}

		public String getTurno_trabajo() {
			return turno_trabajo;
		}

		public void setTurno_trabajo(String turno_trabajo) {
			this.turno_trabajo = turno_trabajo;
		}

		public String getResidencia() {
			return residencia;
		}

		public void setResidencia(String residencia) {
			this.residencia = residencia;
		}

		public Double getSalario() {
			return salario;
		}

		public void setSalario(Double salario) {
			this.salario = salario;
		}
	    

	}


