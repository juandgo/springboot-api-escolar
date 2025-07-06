package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.dto.ProfesorDTO;
import com.dt.registroescolar.api_registro_escolar.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping("/registrar")
    public ResponseEntity<ProfesorDTO> registrarProfesor(@RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO guardado = profesorService.registrarProfesor(profesorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarProfesores() {
        return ResponseEntity.ok(profesorService.listarProfesores());
    }

    @GetMapping("/buscar/id/{idPersona}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idPersona) {
        Optional<ProfesorDTO> profesorDTO = profesorService.buscarPorId(idPersona);
        return profesorDTO.isPresent() ? ResponseEntity.ok(profesorDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
    }

    @GetMapping("/buscar/especialidad/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidad(@PathVariable String especialidad) {
        List<ProfesorDTO> profesoresDTO = profesorService.buscarPorEspecialidad(especialidad);
        if (profesoresDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron profesores con la especialidad: " + especialidad);
        }

        return ResponseEntity.ok(profesoresDTO);
    }

    @PutMapping("/actualizar/{idPersona}")
    public ResponseEntity<?> actualizarProfesor(
            @PathVariable Long idPersona,
            @RequestBody ProfesorDTO profesorDTO) {
        try {
            ProfesorDTO actualizado = profesorService.actualizarProfesor(idPersona, profesorDTO);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idPersona}")
    public ResponseEntity<?> eliminarProfesor(@PathVariable Long idPersona) {
        try {
            profesorService.eliminarProfesor(idPersona);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar: " + e.getMessage());
        }
    }
}
