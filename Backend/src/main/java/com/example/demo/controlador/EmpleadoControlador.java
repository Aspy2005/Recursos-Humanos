package com.example.demo.controlador;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DetallesEmpleadoDTO;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.Repositorio.UsuarioRepositorio;
import com.example.demo.Servicios.CorreoServicio;
import com.example.demo.Servicios.EmpleadoServicio;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Usuario;

@RestController
@RequestMapping("/empleado")

public class EmpleadoControlador {

	
	@Autowired 
	private EmpleadoRepositorio ERepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
    private EmpleadoServicio empleadoServicio;
	
	@Autowired
    private CorreoServicio correoServicio;
		
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, String>> registrarEmpleado(@RequestBody Empleado empleado) {
	    // Guardar el empleado primero
	    Empleado empleadoGuardado = ERepositorio.save(empleado);

	    // Generar contraseña aleatoria
	    String contrasenaGenerada = generarContrasenaAleatoria(10); // Ej: 10 caracteres

	    // Crear el usuario
	    Usuario usuario = new Usuario();
	    usuario.setEmpleado(empleadoGuardado);
	    usuario.setContrasena(contrasenaGenerada);  // <-- Solo aquí va la contraseña
	    usuario.setRol("Empleado");
	    usuarioRepositorio.save(usuario);

	    // Enviar correo
	    correoServicio.enviarCorreo(
	        empleadoGuardado.getCorreo(),
	        "Bienvenido a la empresa",
	        "Tu usuario es: " + empleadoGuardado.getCedulaEmpleado() + "\n" +
	        "Tu contraseña es: " + contrasenaGenerada
	    );

	    // Devolver respuesta
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Empleado y usuario registrados correctamente.");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String generarContrasenaAleatoria(int longitud) {
	    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder contrasena = new StringBuilder();
	    for (int i = 0; i < longitud; i++) {
	        int index = (int) (Math.random() * caracteres.length());
	        contrasena.append(caracteres.charAt(index));
	    }
	    return contrasena.toString();
	}


	
	@GetMapping("/validar-cedula/{cedula}")
	public ResponseEntity<Boolean> validarCedula(@PathVariable String cedula) {
	    if (empleadoServicio.validarCedula(cedula)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(true); // Cédula ya existe
	    }
	    return ResponseEntity.ok(false); // Cédula no existe
	}

	
	@PutMapping("/actualizar")
	public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) throws Exception {
	    // Realiza las validaciones necesarias
	    if (empleado != null) {
	        // Actualiza solo los campos que el empleado puede modificar
	        Empleado empleadoActualizado = empleadoServicio.actualizarEmpleado(empleado);
	        return ResponseEntity.ok(empleadoActualizado);
	    }
	    return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/findByCedula/{cedula}")
	public ResponseEntity<Empleado> obtenerEmpleadoPorCedula(@PathVariable String cedula) {
	    Empleado empleado = ERepositorio.findByCedulaEmpleado(cedula);
	    if (empleado != null) {
	        return ResponseEntity.ok(empleado);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	@PutMapping("/actualizar/{idEmpleado}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String idEmpleado, @RequestBody Empleado empleado) {
	    Optional<Empleado> empleadoExistente = ERepositorio.findById(idEmpleado);
	    if (empleadoExistente.isPresent()) {
	        Empleado empleadoActualizado = empleadoExistente.get();
	        // Actualiza los campos necesarios
	        empleadoActualizado.setNombre(empleado.getNombre());
	        empleadoActualizado.setApellido(empleado.getApellido());
	        empleadoActualizado.setTelefono(empleado.getTelefono());
	        empleadoActualizado.setDireccion(empleado.getDireccion());
	        empleadoActualizado.setFechaIngreso(empleado.getFechaIngreso());
	        empleadoActualizado.setEdad(empleado.getEdad());
	        empleadoActualizado.setCargo(empleado.getCargo());
	        empleadoActualizado.setTurnoTrabajo(empleado.getTurnoTrabajo());
	        empleadoActualizado.setCorreo(empleado.getCorreo());
	        empleadoActualizado.setCiudad(empleado.getCiudad());
	        empleadoActualizado.setSalario(empleado.getSalario());

	        ERepositorio.save(empleadoActualizado);
	        return ResponseEntity.ok(empleadoActualizado);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}

	
	@DeleteMapping("/eliminar/{idEmpleado}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable String idEmpleado) {
	    Optional<Empleado> empleado = ERepositorio.findById(idEmpleado);
	    if (empleado.isPresent()) {
	        ERepositorio.delete(empleado.get());
	        return ResponseEntity.noContent().build();  // Elimina el empleado y responde con HTTP 204
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}





}
