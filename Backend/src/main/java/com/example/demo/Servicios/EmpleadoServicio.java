package com.example.demo.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    public boolean validarCedula(String cedula) {
        // Verificar si existe un empleado con la cédula proporcionada
        return empleadoRepositorio.existsByCedulaEmpleado(cedula);
    }
    
    public Empleado actualizarEmpleado(Empleado empleado) throws Exception {
        Empleado empleadoExistente = empleadoRepositorio.findByCedulaEmpleado(empleado.getCedulaEmpleado());

        if (empleadoExistente == null) {
            throw new Exception("Empleado no encontrado");
        }

        // Actualiza solo los campos permitidos
        if (empleado.getDireccion() != null) {
            empleadoExistente.setDireccion(empleado.getDireccion());
        }
        if (empleado.getTelefono() != null) {
            empleadoExistente.setTelefono(empleado.getTelefono());
        }
        if (empleado.getCorreo() != null) {
            empleadoExistente.setCorreo(empleado.getCorreo());
        }
        if (empleado.getCiudad() != null) {
            empleadoExistente.setCiudad(empleado.getCiudad());
        }

        // Guarda y retorna el empleado actualizado
        return empleadoRepositorio.save(empleadoExistente);
    }

    public boolean eliminarEmpleado(String cedula) {
        Optional<Empleado> empleado = empleadoRepositorio.findById(cedula);

        if (empleado.isPresent()) {
            empleadoRepositorio.deleteById(cedula);
            return true;
        }
        return false;
    }
}
