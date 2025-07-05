package com.dt.registroescolar.api_registro_escolar.service.impl;

import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import com.dt.registroescolar.api_registro_escolar.repository.EstudianteRepository;
import com.dt.registroescolar.api_registro_escolar.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Estudiante registrarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Optional<Estudiante> buscarPorNumeroMatricula(String numeroMatricula) {
        return estudianteRepository.findByNumeroMatricula(numeroMatricula);
    }

    @Override
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);

        if (estudianteOptional.isPresent()) {
            Estudiante estudiante = estudianteOptional.get();
            estudiante.setNombre(estudianteActualizado.getNombre());
            estudiante.setApellido(estudianteActualizado.getApellido());
            estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
            estudiante.setEmail(estudianteActualizado.getEmail());
            estudiante.setTelefono(estudianteActualizado.getTelefono());
            estudiante.setNumeroMatricula(estudianteActualizado.getNumeroMatricula());
            estudiante.setGrado(estudianteActualizado.getGrado());
            return estudianteRepository.save(estudiante);
        } else {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
    }

    @Override
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
