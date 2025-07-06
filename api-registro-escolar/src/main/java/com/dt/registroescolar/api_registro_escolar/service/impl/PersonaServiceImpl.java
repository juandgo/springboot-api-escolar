package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.dto.PersonaDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import com.dt.registroescolar.api_registro_escolar.mapper.PersonaMapper;
import com.dt.registroescolar.api_registro_escolar.repository.PersonaRepository;
import com.dt.registroescolar.api_registro_escolar.service.PersonaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public PersonaDTO registrarPersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.toEntity(personaDTO);
        Persona guardada = personaRepository.save(persona);
        return personaMapper.toDTO(guardada);
    }

    @Override
    public List<PersonaDTO> listarPersonas() {
        return personaRepository.findAll().stream()
                .map(personaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonaDTO> buscarPorId(Long idPersona) {
        return personaRepository.findById(idPersona)
                .map(personaMapper::toDTO);
    }

    @Override
    public Optional<PersonaDTO> buscarPorEmail(String email) {
        return personaRepository.findByEmail(email)
                .map(personaMapper::toDTO);
    }

    @Override
    public List<PersonaDTO> buscarPorApellido(String apellido) {
        return personaRepository.findByApellido(apellido).stream()
                .map(personaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public PersonaDTO actualizarPersona(Long idPersona, PersonaDTO personaActualizadaDTO) {
        Persona personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(() -> new Exception("Persona con ID " + idPersona + " no encontrada"));

        personaMapper.toEntity(personaActualizadaDTO, personaExistente);
        Persona actualizada = personaRepository.save(personaExistente);
        return personaMapper.toDTO(actualizada);
    }

    @Override
    @SneakyThrows
    public void eliminarPersona(Long idPersona) {
        Persona personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(() -> new Exception("Persona con ID " + idPersona + " no encontrada"));
        personaRepository.delete(personaExistente);
    }
}
