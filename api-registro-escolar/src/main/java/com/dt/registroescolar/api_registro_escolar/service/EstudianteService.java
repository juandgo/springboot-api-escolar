package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    Estudiante registrarEstudiante(Estudiante estudiante);

    List<Estudiante> listarEstudiantes();

    Optional<Estudiante> buscarPorId(Long id);

    Optional<Estudiante> buscarPorNumeroMatricula(String numeroMatricula);

    Estudiante actualizarEstudiante(Long id, Estudiante estudiante);

    void eliminarEstudiante(Long id);
}
