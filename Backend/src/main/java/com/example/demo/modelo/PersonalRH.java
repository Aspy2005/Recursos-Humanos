package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "personal_rh")
public class PersonalRH {

    @Id
    @Column(name = "cedula_personal")
    private String cedulaPersonal;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;



    public PersonalRH() {
		super();
	}

	// Getters y setters

    public PersonalRH(String cedulaPersonal, String nombre, String apellido, String correo, String telefono) {
		super();
		this.cedulaPersonal = cedulaPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
	}

	public String getCedulaPersonal() {
        return cedulaPersonal;
    }

    public void setCedulaPersonal(String cedulaPersonal) {
        this.cedulaPersonal = cedulaPersonal;
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

}
