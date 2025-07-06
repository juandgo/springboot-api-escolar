package com.dt.registroescolar.api_registro_escolar.repository;

import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    List<Profesor> findByEspecialidad(String especialidad);

    Optional<Profesor> findByIdPersona(Long idPersona);

    List<Profesor> findByFechaContratacion(LocalDate fechaContratacion);
}
