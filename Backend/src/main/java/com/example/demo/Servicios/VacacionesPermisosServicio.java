package com.example.demo.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorio.VacacionesPermisosRepositorio;
import com.example.demo.modelo.VacacionesPermisos;

@Service
public class VacacionesPermisosServicio {

    @Autowired
    private VacacionesPermisosRepositorio repositorio;

    public List<VacacionesPermisos> listarTodos() {
        return repositorio.findAllByOrderByFechaInicioDesc();
    }

    public VacacionesPermisos actualizarEstado(Integer id, String nuevoEstado) {
        VacacionesPermisos permiso = repositorio.findById(id).orElseThrow();
        permiso.setEstado(nuevoEstado);
        return repositorio.save(permiso);
    }
}
