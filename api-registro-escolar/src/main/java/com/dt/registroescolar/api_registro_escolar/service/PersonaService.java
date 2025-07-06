package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    PersonaDTO registrarPersona(PersonaDTO personaDTO);

    List<PersonaDTO> listarPersonas();

    Optional<PersonaDTO> buscarPorId(Long idPersona);

    Optional<PersonaDTO> buscarPorEmail(String email);

    List<PersonaDTO> buscarPorApellido(String apellido);

    PersonaDTO actualizarPersona(Long idPersona, PersonaDTO personaActualizada);

    void eliminarPersona(Long idPersona);
}
