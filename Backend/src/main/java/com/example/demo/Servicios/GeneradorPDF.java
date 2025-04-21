package com.example.demo.Servicios;

import com.example.demo.modelo.Empleado;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class GeneradorPDF {

    public static String generarReporte(String tipo, Empleado empleado) throws IOException, DocumentException {
        String nombreArchivo = empleado.getCedulaEmpleado() + "_" + tipo + "_" + LocalDate.now() + ".pdf";
        String rutaDirectorio = "reportes/" + tipo.toLowerCase();
        String rutaCompleta = rutaDirectorio + "/" + nombreArchivo;

        // Crear directorios si no existen
        new java.io.File(rutaDirectorio).mkdirs();

        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
        documento.open();

        documento.add(new Paragraph("REPORTE DE " + tipo.toUpperCase()));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Empleado: " + empleado.getNombre()));
        documento.add(new Paragraph("Cédula: " + empleado.getCedulaEmpleado()));
        documento.add(new Paragraph("Fecha de generación: " + LocalDate.now()));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Este es un reporte generado automáticamente."));

        documento.close();

        return rutaCompleta;
    }
}
