package com.dt.registroescolar.api_registro_escolar.repository;

import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByNumeroMatricula(String numeroMatricula);
}
