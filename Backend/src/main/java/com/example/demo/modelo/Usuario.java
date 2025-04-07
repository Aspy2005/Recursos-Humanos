package com.example.demo.modelo;
import jakarta.persistence.*;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
	@Column(name = "id_usuario")
	private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_coordinadora")
    private Coordinadora coordinadora;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "contraseña", length = 100)
    private String contraseña;

    @Column(name = "rol", length = 30)
    private String rol;

}
