package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, String> {
	@Query("SELECT e FROM Empleado e WHERE e.cedulaEmpleado IN (SELECT a.empleado.cedulaEmpleado FROM Asistencia a UNION SELECT p.empleado.cedulaEmpleado FROM PQR p)")
	List<Empleado> findEmpleadosConRegistros();
}
