package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import com.dt.registroescolar.api_registro_escolar.repository.ProfesorRepository;
import com.dt.registroescolar.api_registro_escolar.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public Profesor registrarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> buscarPorId(Long id) {
        return profesorRepository.findById(id);
    }

    @Override
    public Optional<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesorRepository.findByEspecialidad(especialidad);
    }


    @Override
    public Profesor actualizarProfesor(Long id, Profesor profesorActualizado) {
        return profesorRepository.findById(id).map(profesor -> {
            profesor.setNombre(profesorActualizado.getNombre());
            profesor.setApellido(profesorActualizado.getApellido());
            profesor.setEmail(profesorActualizado.getEmail());
            profesor.setFechaNacimiento(profesorActualizado.getFechaNacimiento());
            profesor.setEspecialidad(profesorActualizado.getEspecialidad());
            profesor.setFechaContratacion(profesorActualizado.getFechaContratacion());
            return profesorRepository.save(profesor);
        }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
    }

    @Override
    public void eliminarProfesor(Long id) {
        profesorRepository.deleteById(id);
    }
}
