package com.example.demo.Repositorio;

import com.example.demo.modelo.PQR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PQRRepositorio extends JpaRepository<PQR, Integer> {
    List<PQR> findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(String cedulaEmpleado);
}
