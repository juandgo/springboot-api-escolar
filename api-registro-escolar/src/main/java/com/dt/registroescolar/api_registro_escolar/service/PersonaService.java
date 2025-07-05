package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    Persona registrarPersona(Persona persona);

    List<Persona> listarPersonas();

    Optional<Persona> buscarPorId(Long idPersona);

    Optional<Persona> buscarPorEmail(String email);

    List<Persona> buscarPorApellido(String apellido);

    Persona actualizarPersona(Long idPersona, Persona personaActualizada);

    void eliminarPersona(Long idPersona);
}
