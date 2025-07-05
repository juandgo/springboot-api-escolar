package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import com.dt.registroescolar.api_registro_escolar.repository.PersonaRepository;
import com.dt.registroescolar.api_registro_escolar.service.PersonaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona registrarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> buscarPorId(Long idPersona) {
        return personaRepository.findById(idPersona);
    }

    @Override
    public Optional<Persona> buscarPorEmail(String email) {
        return personaRepository.findByEmail(email);
    }

    @Override
    public List<Persona> buscarPorApellido(String apellido) {
        return personaRepository.findByApellido(apellido);
    }

    @Override
    @SneakyThrows
    public Persona actualizarPersona(Long idPersona, Persona personaActualizada) {
        Persona personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(() -> new Exception("Persona con ID " + idPersona + " no encontrada"));

        personaExistente.setNombre(personaActualizada.getNombre());
        personaExistente.setApellido(personaActualizada.getApellido());
        personaExistente.setFechaNacimiento(personaActualizada.getFechaNacimiento());
        personaExistente.setEmail(personaActualizada.getEmail());
        personaExistente.setTelefono(personaActualizada.getTelefono());

        return personaRepository.save(personaExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarPersona(Long idPersona) {
        Persona personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(() -> new Exception("Persona con ID " + idPersona + " no encontrada"));

        personaRepository.deleteById(idPersona);
    }
}
