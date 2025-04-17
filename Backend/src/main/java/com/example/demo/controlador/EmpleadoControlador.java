package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.Servicios.EmpleadoServicio;
import com.example.demo.modelo.Empleado;

@RestController
@RequestMapping("/empleado")

public class EmpleadoControlador {

	
	@Autowired 
	private EmpleadoRepositorio ERepositorio;
	
	@Autowired
    private EmpleadoServicio empleadoServicio;
		
	@PostMapping("/registrar")
	public Empleado registrarEmpleado(@RequestBody Empleado empleado) {
	    return ERepositorio.save(empleado);
	}
	
	@GetMapping("/validar-cedula/{cedula}")
	public ResponseEntity<Boolean> validarCedula(@PathVariable String cedula) {
	    if (empleadoServicio.validarCedula(cedula)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(true); // Cédula ya existe
	    }
	    return ResponseEntity.ok(false); // Cédula no existe
	}


}
