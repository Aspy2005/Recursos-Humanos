package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.VacacionesPermisos;

@Repository
public interface VacacionesPermisosRepositorio extends JpaRepository<VacacionesPermisos, Integer> {
    List<VacacionesPermisos> findAllByOrderByFechaInicioDesc();
    List<VacacionesPermisos> findByEmpleado_CedulaEmpleado(String cedulaEmpleado);

}
