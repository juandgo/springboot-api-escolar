package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.dto.ProfesorDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import com.dt.registroescolar.api_registro_escolar.mapper.ProfesorMapper;
import com.dt.registroescolar.api_registro_escolar.repository.ProfesorRepository;
import com.dt.registroescolar.api_registro_escolar.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private ProfesorMapper profesorMapper;

    @Override
    public ProfesorDTO registrarProfesor(ProfesorDTO profesorDTO) {
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Profesor guardado = profesorRepository.save(profesor);
        return profesorMapper.toDTO(guardado);
    }

    @Override
    public List<ProfesorDTO> listarProfesores() {
        return profesorRepository.findAll()
                .stream()
                .map(profesorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProfesorDTO> buscarPorId(Long id) {
        return profesorRepository.findById(id).map(profesorMapper::toDTO);
    }

    @Override
    public List<ProfesorDTO> buscarPorEspecialidad(String especialidad) {
        List<Profesor> profesoresDTO = profesorRepository.findByEspecialidad(especialidad);
        return profesoresDTO.stream()
                .map(profesorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorDTO actualizarProfesor(Long id, ProfesorDTO profesorActualizadoDTO) {
        return profesorRepository.findById(id).map(profesor -> {
            profesorMapper.toEntity(profesorActualizadoDTO, profesor);
            return profesorMapper.toDTO(profesorRepository.save(profesor));
        }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
    }

    @Override
    public void eliminarProfesor(Long id) {
        profesorRepository.deleteById(id);
    }
}
