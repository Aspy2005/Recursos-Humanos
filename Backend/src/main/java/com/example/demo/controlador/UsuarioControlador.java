package com.example.demo.controlador;

import com.example.demo.Repositorio.UsuarioRepositorio;
import com.example.demo.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String cedula = loginData.get("cedula");
        String contrasena = loginData.get("contrasena");

        // Buscar por cédula del empleado o del personal RH
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByEmpleadoCedulaEmpleadoOrPersonalRHCedulaPersonal(cedula, cedula);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContrasena().equals(contrasena)) {
                // Retornar rol y cédula
                return ResponseEntity.ok(Map.of(
                        "rol", usuario.getRol(),
                        "cedula", cedula
                ));
            }
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}
