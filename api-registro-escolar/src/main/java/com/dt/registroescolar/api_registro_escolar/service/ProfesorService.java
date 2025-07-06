package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.dto.ProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    ProfesorDTO registrarProfesor(ProfesorDTO profesorDTO);

    List<ProfesorDTO> listarProfesores();

    Optional<ProfesorDTO> buscarPorId(Long id);

    List<ProfesorDTO> buscarPorEspecialidad(String especialidad);

    ProfesorDTO actualizarProfesor(Long id, ProfesorDTO profesorActualizadoDTO);

    void eliminarProfesor(Long id);
}
