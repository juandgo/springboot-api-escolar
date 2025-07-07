package com.dt.registroescolar.api_registro_escolar.mapper;

import com.dt.registroescolar.api_registro_escolar.dto.ProfesorDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Profesor toEntity(ProfesorDTO profesorDTO) {
        return modelMapper.map(profesorDTO, Profesor.class);
    }

    public void toEntity(ProfesorDTO profesorDTO, Profesor profesorExistente) {
        modelMapper.map(profesorDTO, profesorExistente);
    }

    public ProfesorDTO toDTO(Profesor profesor) {
//        return modelMapper.map(profesor, ProfesorDTO.class);
        ProfesorDTO dto = modelMapper.map(profesor, ProfesorDTO.class);
        System.out.println("ID mapeado: " + dto.getIdPersona()); // debug
        return dto;
    }

}
