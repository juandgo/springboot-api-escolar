package com.dt.registroescolar.api_registro_escolar.mapper;

import com.dt.registroescolar.api_registro_escolar.dto.EstudianteDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Estudiante toEntity(EstudianteDTO estudianteDTO) {
        return modelMapper.map(estudianteDTO, Estudiante.class);
    }

    public void toEntity(EstudianteDTO estudianteDTO, Estudiante estudianteExistente) {
        modelMapper.map(estudianteDTO, estudianteExistente);
    }

    public EstudianteDTO toDTO(Estudiante estudiante) {
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }
}
