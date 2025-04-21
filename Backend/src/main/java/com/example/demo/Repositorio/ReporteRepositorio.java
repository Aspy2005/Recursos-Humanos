package com.example.demo.Repositorio;

import com.example.demo.modelo.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepositorio extends JpaRepository<Reporte, Integer> {

    List<Reporte> findByEmpleado_CedulaEmpleado(String cedulaEmpleado);

    List<Reporte> findByTipo(String tipo);

    List<Reporte> findByEmpleado_CedulaEmpleadoAndTipo(String cedulaEmpleado, String tipo);
}