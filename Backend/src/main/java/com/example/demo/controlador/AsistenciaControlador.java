package com.example.demo.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Servicios.AsistenciaServicio;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio asistenciaServicio;

    @PostMapping("/entrada/{cedula}")
    public ResponseEntity<String> registrarEntrada(@PathVariable String cedula) {
        try {
            String mensaje = asistenciaServicio.registrarEntrada(cedula);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/salida/{cedula}")
    public ResponseEntity<String> registrarSalida(@PathVariable String cedula) {
        try {
            String mensaje = asistenciaServicio.registrarSalida(cedula);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/borrarEntrada")
    public ResponseEntity<Map<String, String>> borrarEntrada(@RequestBody Map<String, String> request) {
        String cedula = request.get("cedula");
        String mensaje = asistenciaServicio.borrarEntrada(cedula);
        
        // Devuelve un JSON con el mensaje
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        
        return ResponseEntity.ok(response); // Devuelve como JSON
    }

}
