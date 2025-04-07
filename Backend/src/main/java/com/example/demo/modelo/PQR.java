package com.example.demo.modelo;
import jakarta.persistence.*;

@Entity
public class PQR {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pqr")
	@SequenceGenerator(name = "seq_pqr", sequenceName = "seq_pqr", allocationSize = 1)
	@Column(name = "id_pqr")
	private Integer idPqr;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

}
