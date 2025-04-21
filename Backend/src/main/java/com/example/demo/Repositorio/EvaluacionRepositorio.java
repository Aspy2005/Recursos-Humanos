package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo.modelo.Evaluacion;

public interface EvaluacionRepositorio extends JpaRepository<Evaluacion, Integer> {
    // Método para obtener las últimas 5 evaluaciones de un empleado por su cédula
    List<Evaluacion> findTop5ByEmpleadoCedulaEmpleadoOrderByFechaDesc(String cedulaEmpleado);
    List<Evaluacion> findByEmpleado_CedulaEmpleado(String cedulaEmpleado);

}
