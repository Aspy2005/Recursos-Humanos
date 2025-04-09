package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

}
