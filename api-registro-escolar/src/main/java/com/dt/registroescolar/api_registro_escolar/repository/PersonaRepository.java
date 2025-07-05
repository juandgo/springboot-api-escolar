package com.dt.registroescolar.api_registro_escolar.repository;

import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);
    Optional<Persona> findByIdPersona(Long idPersona);
    List<Persona> findByApellido(String apellido);
    List<Persona> findByNombre(String nombre);
}
