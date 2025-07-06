package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.dto.EstudianteDTO;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO);

    List<EstudianteDTO> listarEstudiantes();

    Optional<EstudianteDTO> buscarPorId(Long id);

    Optional<EstudianteDTO> buscarPorNumeroMatricula(String numeroMatricula);

    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);

    void eliminarEstudiante(Long id);
}
