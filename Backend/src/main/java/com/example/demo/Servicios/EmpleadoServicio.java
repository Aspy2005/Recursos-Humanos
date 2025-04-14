package com.example.demo.Servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DetallesEmpleadoDTO;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.modelo.Empleado;

@Service
public class EmpleadoServicio {
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    public List<DetallesEmpleadoDTO> obtenerEmpleadosConRegistros() {
        List<Empleado> empleados = empleadoRepositorio.findEmpleadosConRegistros();
        return empleados.stream()
                        .map(empleado -> new DetallesEmpleadoDTO(empleado, null, null)) // Aquí falta asistencia y pqr
                        .collect(Collectors.toList());
    }	
}
