package com.example.demo.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmpleadoCedulaEmpleadoOrPersonalRHCedulaPersonal(String cedulaEmpleado, String cedulaPersonal);
}

