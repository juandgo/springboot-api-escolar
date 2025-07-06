package com.dt.registroescolar.api_registro_escolar.mapper;

import com.dt.registroescolar.api_registro_escolar.dto.PersonaDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Persona toEntity(PersonaDTO personaDTO) {
        return modelMapper.map(personaDTO, Persona.class);
    }

    public void toEntity(PersonaDTO personaDTO, Persona personaExistente) {
        modelMapper.map(personaDTO, personaExistente);
    }

    public PersonaDTO toDTO(Persona persona) {
        return modelMapper.map(persona, PersonaDTO.class);
    }
}
