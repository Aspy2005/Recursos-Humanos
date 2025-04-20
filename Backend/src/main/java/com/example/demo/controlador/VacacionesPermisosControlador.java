package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.SolicitudVacacionPermisoDTO;
import com.example.demo.Servicios.VacacionesPermisosServicio;
import com.example.demo.modelo.VacacionesPermisos;

@RestController
@RequestMapping("/vacaciones-permisos")
public class VacacionesPermisosControlador {

    @Autowired
    private VacacionesPermisosServicio servicio;

    @GetMapping
    public List<VacacionesPermisos> obtenerTodos() {
        return servicio.listarTodos();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<VacacionesPermisos> actualizarEstado(
            @PathVariable Integer id,
            @RequestParam String estado) {
        VacacionesPermisos actualizado = servicio.actualizarEstado(id, estado);
        return ResponseEntity.ok(actualizado);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearSolicitud(@RequestBody SolicitudVacacionPermisoDTO dto) {
        try {
            String mensaje = servicio.crearSolicitud(dto);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensaje);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

}
