package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", referencedColumnName = "cedula_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cedula_personal", referencedColumnName = "cedula_personal")
    private PersonalRH personalRH;

    @Column(name = "contrasena", length = 100)
    private String contrasena;

    @Column(name = "rol", length = 30)
    private String rol;

	public Usuario() {
		super();
	}

	public Usuario(Integer idUsuario, Empleado empleado, PersonalRH personalRH, String contrasena, String rol) {
		super();
		this.idUsuario = idUsuario;
		this.empleado = empleado;
		this.personalRH = personalRH;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
    
    
}