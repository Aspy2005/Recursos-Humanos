package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ReporteRequest;
import com.example.demo.Servicios.ReporteServicio;
import com.example.demo.modelo.Reporte;

import java.io.File;

@RestController
@RequestMapping("/reportes")
public class ReporteControlador {

    @Autowired
    private ReporteServicio reporteService;

    @PostMapping("/generar")
    public ResponseEntity<?> generarReporte(@RequestBody ReporteRequest reporteRequest) {
        try {
            Reporte reporte = reporteService.generarReporte(
                reporteRequest.getIdEmpleado(), 
                reporteRequest.getTipo(), 
                reporteRequest.getCedulaRH());
            return ResponseEntity.ok(reporte);
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al generar el reporte: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }

    }

    @GetMapping("/descargar/{id}")
    public ResponseEntity<Resource> descargarReporte(@PathVariable Integer id) {
        Reporte reporte = reporteService.obtenerReportePorId(id);
        File archivo = new File(reporte.getRuta());

        if (!archivo.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        FileSystemResource resource = new FileSystemResource(archivo);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getName() + "\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(resource);
    }
}
