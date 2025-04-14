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

    @Autowired
    private CorreoServicio correoServicio;

    public List<VacacionesPermisos> listarTodos() {
        return repositorio.findAllByOrderByFechaInicioDesc();
    }

    public VacacionesPermisos actualizarEstado(Integer id, String nuevoEstado) {
        VacacionesPermisos permiso = repositorio.findById(id).orElseThrow();
        permiso.setEstado(nuevoEstado);
        VacacionesPermisos actualizado = repositorio.save(permiso);

        // Enviar correo
        String correoEmpleado = permiso.getEmpleado().getCorreo();
        String asunto = "Respuesta a tu solicitud de " + permiso.getTipo();
        String cuerpo = "Hola " + permiso.getEmpleado().getNombre() + ",\n\n" +
                        "Tu solicitud del " + permiso.getFechaInicio() + " al " + permiso.getFechaFin() +
                        " ha sido *" + nuevoEstado.toUpperCase() + "*.\n\n" +
                        "Saludos,\nGestión Humana";

        correoServicio.enviarCorreo(correoEmpleado, asunto, cuerpo);
        
        return actualizado;
    }
}
