package com.example.demo.Repositorio;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(String cedulaEmpleado);

	Optional<Asistencia> findByEmpleadoAndFecha(Empleado empleado, LocalDate hoy);

	List<Asistencia> findByEmpleado_CedulaEmpleado(String idEmpleado);
}
