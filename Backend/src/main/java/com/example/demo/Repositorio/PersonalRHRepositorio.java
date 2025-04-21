package com.example.demo.Repositorio;

import com.example.demo.modelo.PersonalRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRHRepositorio extends JpaRepository<PersonalRH, String> {
    PersonalRH findByCedulaPersonal(String cedulaPersonal);
}