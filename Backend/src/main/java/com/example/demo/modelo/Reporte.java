package com.example.demo.modelo;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Reporte {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reporte")
	@SequenceGenerator(name = "seq_reporte", sequenceName = "seq_reporte", allocationSize = 1)
	@Column(name = "id_reporte")
	private Integer idReporte;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    @Column(name = "ruta", length = 255)
    private String ruta;

}
