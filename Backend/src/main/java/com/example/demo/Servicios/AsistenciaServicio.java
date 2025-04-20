package com.example.demo.Servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorio.AsistenciaRepositorio;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Empleado;

@Service
public class AsistenciaServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepo;

    @Autowired
    private AsistenciaRepositorio asistenciaRepo;

    public String registrarEntrada(String cedula) {
        Empleado empleado = empleadoRepo.findByCedulaEmpleado(cedula);
        LocalDate hoy = LocalDate.now();
        Optional<Asistencia> existente = asistenciaRepo.findByEmpleadoAndFecha(empleado, hoy);

        // Flujo Alterno 1: Si el empleado ya ha registrado su entrada en el mismo día
        if (existente.isPresent() && existente.get().getHoraEntrada() != null) {
            return "La hora de entrada ya fue registrada";
        }

        // Si ya existe un registro, pero sin hora de salida, no permitir otra entrada
        if (existente.isPresent() && existente.get().getHoraSalida() == null) {
            throw new RuntimeException("Ya has registrado tu entrada. Debes registrar tu salida primero.");
        }

        // Crear o actualizar el registro de entrada
        Asistencia asistencia = existente.orElse(new Asistencia());
        asistencia.setEmpleado(empleado);
        asistencia.setFecha(hoy);
        asistencia.setHoraEntrada(LocalTime.now());
        asistenciaRepo.save(asistencia);

        return "Hora de entrada registrada";
    }
    
    public String borrarEntrada(String cedula) {
        Empleado empleado = empleadoRepo.findByCedulaEmpleado(cedula);
        LocalDate hoy = LocalDate.now();
        Optional<Asistencia> existente = asistenciaRepo.findByEmpleadoAndFecha(empleado, hoy);

        // Verificamos si existe una entrada registrada para ese día
        if (existente.isPresent() && existente.get().getHoraEntrada() != null) {
            // Borramos la entrada registrada
            asistenciaRepo.delete(existente.get());
            return "Entrada borrada con éxito. Ahora puedes registrar una nueva entrada.";
        } else {
            // Si no hay entrada registrada, mostramos un mensaje informativo
            return "No hay una entrada registrada para hoy que puedas borrar.";
        }
    }

    public String registrarSalida(String cedula) {
        Empleado empleado = empleadoRepo.findByCedulaEmpleado(cedula);
        LocalDate hoy = LocalDate.now();

        // Flujo Alterno 2: Si el empleado intenta registrar la salida sin haber registrado la entrada
        Asistencia asistencia = asistenciaRepo.findByEmpleadoAndFecha(empleado, hoy)
            .orElseThrow(() -> new RuntimeException("No se puede registrar la salida sin haber registrado la entrada"));

        // Validación si ya se registró la salida
        if (asistencia.getHoraSalida() != null) {
            throw new RuntimeException("La hora de salida ya fue registrada.");
        }

        // Verificación de que la hora de salida no sea antes de la hora de entrada
        if (asistencia.getHoraEntrada().isAfter(LocalTime.now())) {
            throw new RuntimeException("La hora de salida no puede ser antes de la hora de entrada.");
        }

        // Registrar la salida
        asistencia.setHoraSalida(LocalTime.now());

        // Calcular las horas trabajadas, asegurando que no sea negativo
        long minutos = ChronoUnit.MINUTES.between(asistencia.getHoraEntrada(), asistencia.getHoraSalida());
        if (minutos < 0) {
            throw new RuntimeException("El tiempo trabajado no puede ser negativo.");
        }
        asistencia.setHorasTrabajadas(minutos / 60.0);
        asistenciaRepo.save(asistencia);

        return "Hora de salida registrada. Horas trabajadas: " + asistencia.getHorasTrabajadas();
    }
}
