package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.dto.EstudianteDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import com.dt.registroescolar.api_registro_escolar.mapper.EstudianteMapper;
import com.dt.registroescolar.api_registro_escolar.repository.EstudianteRepository;
import com.dt.registroescolar.api_registro_escolar.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Override
    public EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Estudiante guardado = estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(guardado);
    }

    @Override
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(estudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstudianteDTO> buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::toDTO);
    }

    @Override
    public Optional<EstudianteDTO> buscarPorNumeroMatricula(String numeroMatricula) {
        return estudianteRepository.findByNumeroMatricula(numeroMatricula)
                .map(estudianteMapper::toDTO);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudianteExistente = estudianteOptional.get();
            estudianteMapper.toEntity(estudianteDTO, estudianteExistente); // actualiza solo campos del DTO
            Estudiante actualizado = estudianteRepository.save(estudianteExistente);
            return estudianteMapper.toDTO(actualizado);
        } else {
            throw new RuntimeException("Estudiante no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
