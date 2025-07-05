package com.dt.registroescolar.api_registro_escolar.service;

import com.dt.registroescolar.api_registro_escolar.entity.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    Profesor registrarProfesor(Profesor profesor);

    List<Profesor> listarProfesores();

    Optional<Profesor> buscarPorId(Long id);
    Optional<Profesor> buscarPorEspecialidad(String especialidad);

    Profesor actualizarProfesor(Long id, Profesor profesorActualizado);

    void eliminarProfesor(Long id);
}
