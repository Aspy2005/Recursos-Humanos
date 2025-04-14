package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.Repositorio.EvaluacionRepositorio;
import com.example.demo.Repositorio.AsistenciaRepositorio;
import com.example.demo.Repositorio.PQRRepositorio;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Evaluacion;
import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.PQR;
import com.example.demo.DTO.DetallesEmpleadoDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/evaluacion")
public class EvaluacionControlador {

    private static final String CEDULA_EMPLEADO = "cedulaEmpleado";

    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private PQRRepositorio pqrRepositorio;
    
    // Obtener empleados con registros disponibles
    @GetMapping("/empleados-con-registros")
    public List<Empleado> obtenerEmpleadosConRegistros() {
        return empleadoRepositorio.findEmpleadosConRegistros(); // Método en repositorio para obtener empleados con registros de asistencia o PQR
    }

    // Obtener los detalles de un empleado, incluyendo los últimos 5 registros de asistencia y PQR
    @GetMapping("/detalles/{cedulaEmpleado}")
    public ResponseEntity<?> obtenerDetallesEmpleado(@PathVariable String cedulaEmpleado) {
        Optional<Empleado> empleadoOptional = empleadoRepositorio.findById(cedulaEmpleado);

        if (empleadoOptional.isEmpty()) {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }

        Empleado empleado = empleadoOptional.get();

        // Obtener los últimos 5 registros de Asistencia
        List<Asistencia> asistencia = asistenciaRepositorio.findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(cedulaEmpleado);

        // Obtener los últimos 5 registros de PQR
        List<PQR> pqr = pqrRepositorio.findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(cedulaEmpleado);

        if (asistencia.isEmpty() || pqr.isEmpty()) {
            return new ResponseEntity<>("No hay datos suficientes para evaluar este empleado", HttpStatus.BAD_REQUEST);
        }

        // Crear el DTO con los datos del empleado, asistencia y PQR
        DetallesEmpleadoDTO detalles = new DetallesEmpleadoDTO(empleado, asistencia, pqr);

        // Devolver el DTO en la respuesta
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    // Registrar la evaluación para un empleado
    @PostMapping("/registrar/{cedulaEmpleado}")
    public ResponseEntity<Map<String, String>> registrarEvaluacion(
            @PathVariable(CEDULA_EMPLEADO) String cedulaEmpleado,
            @RequestBody Evaluacion evaluacion) {

        Optional<Empleado> empleadoOptional = empleadoRepositorio.findById(cedulaEmpleado);

        if (empleadoOptional.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
        		    .body(Map.of("mensaje", "No se encontró un empleado con la cédula proporcionada."));
        }

        Empleado empleado = empleadoOptional.get();
        evaluacion.setEmpleado(empleado);

        evaluacionRepositorio.save(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED)
        	    .body(Map.of("mensaje", "Evaluación registrada exitosamente para el empleado con cédula: " + cedulaEmpleado));
    }
}
