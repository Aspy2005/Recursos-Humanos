package com.example.demo.Repositorio;

import com.example.demo.modelo.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(String cedulaEmpleado);
}
