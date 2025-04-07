package com.example.demo.modelo;

import jakarta.persistence.*;
@Entity
public class Coordinadora {

    @Id
    @Column(name = "id_coordinadora")
    private Integer idCoordinadora;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

}
