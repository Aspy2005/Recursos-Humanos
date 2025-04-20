package com.example.demo.Servicios;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.SolicitudVacacionPermisoDTO;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.Repositorio.VacacionesPermisosRepositorio;
import com.example.demo.modelo.VacacionesPermisos;
import com.example.demo.modelo.Empleado;

@Service
public class VacacionesPermisosServicio {

    @Autowired
    private VacacionesPermisosRepositorio repositorio;

    @Autowired
    private CorreoServicio correoServicio;
    
    @Autowired
    private EmpleadoRepositorio empleadoRepo;

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

    public String crearSolicitud(SolicitudVacacionPermisoDTO dto) {
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        Empleado empleado = empleadoRepo.findByCedulaEmpleado(dto.getCedulaEmpleado());
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado");
        }

        VacacionesPermisos solicitud = new VacacionesPermisos();
        solicitud.setEmpleado(empleado);
        solicitud.setTipo(dto.getTipo());
        solicitud.setFechaInicio(dto.getFechaInicio());
        solicitud.setFechaFin(dto.getFechaFin());
        solicitud.setMotivo(dto.getMotivo());
        solicitud.setObservaciones(dto.getObservaciones());
        solicitud.setDiasSolicitados((int) ChronoUnit.DAYS.between(dto.getFechaInicio(), dto.getFechaFin()) + 1);
        solicitud.setEstado("Pendiente");

        repositorio.save(solicitud);
        return "Solicitud enviada con éxito, pendiente de aprobación";
    }
}
