package com.example.demo.Servicios;

import com.example.demo.Repositorio.*;
import com.example.demo.modelo.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteServicio {

    @Autowired
    private ReporteRepositorio reporteRepository;

    @Autowired
    private EmpleadoRepositorio empleadoRepository;

    @Autowired
    private PersonalRHRepositorio personalRHRepository;

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private VacacionesPermisosRepositorio vacacionesPermisosRepositorio;

    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;

    public Reporte generarReporte(String idEmpleado, String tipo, String cedulaRH) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        PersonalRH personalRH = personalRHRepository.findById(cedulaRH)
            .orElseThrow(() -> new IllegalArgumentException("Personal RH no encontrado"));

        String nombreArchivo = tipo + "_" + empleado.getCedulaEmpleado() + "_" + LocalDate.now() + ".pdf";
        String carpeta = "reportes/" + tipo.toLowerCase();
        String ruta = carpeta + "/" + nombreArchivo;

        // Crear carpeta si no existe
        new File(carpeta).mkdirs();

        // Crear el documento
        Document document = new Document();
        
        try {
            // Crear el escritor y asociarlo con el documento
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();  // Abrir el documento

            document.add(new Paragraph("REPORTE DE " + tipo.toUpperCase()));
            document.add(new Paragraph("Empleado: " + empleado.getNombre()));
            document.add(new Paragraph("Cédula: " + empleado.getCedulaEmpleado()));
            document.add(new Paragraph("Fecha de generación: " + LocalDate.now()));
            document.add(new Paragraph("\n"));

            switch (tipo.toLowerCase()) {
                case "asistencia":
                    List<Asistencia> asistencias = asistenciaRepositorio.findByEmpleado_CedulaEmpleado(idEmpleado);
                    if (asistencias.isEmpty()) {
                        document.add(new Paragraph("No hay registros de asistencia."));
                    } else {
                        for (Asistencia a : asistencias) {
                            document.add(new Paragraph(
                                "Fecha: " + a.getFecha() +
                                " | Entrada: " + a.getHoraEntrada() +
                                " | Salida: " + a.getHoraSalida() +
                                " | Horas trabajadas: " + a.getHorasTrabajadas()
                            ));
                        }
                    }
                    break;

                case "permiso":
                    List<VacacionesPermisos> permisos = vacacionesPermisosRepositorio.findByEmpleado_CedulaEmpleado(idEmpleado);
                    if (permisos.isEmpty()) {
                        document.add(new Paragraph("No hay solicitudes de permiso/vacaciones."));
                    } else {
                        for (VacacionesPermisos p : permisos) {
                            document.add(new Paragraph(
                                "Tipo: " + p.getTipo() +
                                " | Estado: " + p.getEstado() +
                                " | Desde: " + p.getFechaInicio() +
                                " | Hasta: " + p.getFechaFin() +
                                " | Motivo: " + p.getMotivo() +
                                " | Observaciones: " + p.getObservaciones()
                            ));
                        }
                    }
                    break;

                case "desempeño":
                case "desempeno":  // Por si el usuario pone sin tilde
                    List<Evaluacion> evaluaciones = evaluacionRepositorio.findByEmpleado_CedulaEmpleado(idEmpleado);
                    if (evaluaciones.isEmpty()) {
                        document.add(new Paragraph("No hay evaluaciones de desempeño registradas."));
                    } else {
                        for (Evaluacion e : evaluaciones) {
                            document.add(new Paragraph(
                                "Fecha: " + e.getFecha() +
                                " | Puntaje: " + e.getPuntajeDesempeno() +
                                " | Observaciones: " + e.getObservaciones()
                            ));
                        }
                    }
                    break;

                default:
                    document.add(new Paragraph("Tipo de reporte no válido: " + tipo));
                    break;
            }

            document.close();
            writer.close();

        } catch (FileNotFoundException | DocumentException e) {
            throw new RuntimeException("Error al generar el PDF: " + e.getMessage(), e);
        }

        Reporte reporte = new Reporte();
        reporte.setEmpleado(empleado);
        reporte.setPersonalRH(personalRH);
        reporte.setTipo(tipo);
        reporte.setFechaGeneracion(LocalDate.now());
        reporte.setRuta(ruta);

        return reporteRepository.save(reporte);
    }

    public Reporte obtenerReportePorId(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado"));
    }
}
