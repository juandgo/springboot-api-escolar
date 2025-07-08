package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.dto.EstudianteDTO;
import com.dt.registroescolar.api_registro_escolar.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin("*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteRegistrado = estudianteService.registrarEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/buscar/id/{idEstudiante}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idEstudiante) {
        Optional<EstudianteDTO> estudianteDTO = estudianteService.buscarPorId(idEstudiante);
        return estudianteDTO.isPresent() ? ResponseEntity.ok(estudianteDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
    }

    @GetMapping("/buscar/matricula/{numeroMatricula}")
    public ResponseEntity<?> buscarPorNumeroMatricula(@PathVariable String numeroMatricula) {
        Optional<EstudianteDTO> estudianteDTO = estudianteService.buscarPorNumeroMatricula(numeroMatricula);
        return estudianteDTO.isPresent() ? ResponseEntity.ok(estudianteDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteDTO estudianteDTO) {

        try {
            EstudianteDTO actualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Long id) {
        try {
            estudianteService.eliminarEstudiante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar: " + e.getMessage());
        }
    }
}
