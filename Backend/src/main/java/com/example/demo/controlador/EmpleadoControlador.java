package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.modelo.Empleado;

@RestController
@RequestMapping("/empleado")

public class EmpleadoControlador {

	
	@Autowired 
	private EmpleadoRepositorio ERepositorio;
	
	@PostMapping("/registrar")
	public Empleado registrarEmpleado(@RequestBody Empleado empleado) {
	    return ERepositorio.save(empleado);
	}

}
